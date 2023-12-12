export class SessionSbService {
    RESOURCES_URL;
    BROWSER_STORAGE_ITEM_NAME;
    _currentToken;
    username;
    authenticated;

    constructor(resourcesURL, browserStorageItemName) {
        this.RESOURCES_URL = resourcesURL;
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        this._currentToken = this.getTokenFromBrowserStorage();
        this.getTokenFromBrowserStorage();
        this.username = window.localStorage.getItem("Username");
        this.authenticated = this.isAuthenticated();
    }

    // Logs on to the backend, and retrieves user details and the JWT authentication token from the backend
    async asyncSignIn(email, password) {
        const body = JSON.stringify({ email: email, password: password });
        let response = await fetch(this.RESOURCES_URL + "/login",
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json'},
                body: body,
                credentials: "include"
            })
        if (response.ok) {
            let user = await response.json();
            this.username = user.name;
            this.saveTokenIntoBrowserStorage(
                response.headers.get("Authorization"),
                user);
            console.log(user)
            return user;
        }
        console.log(response)
        return null;
    }

    // Discards user details and the JWT authentication token from the service.
    signOut() {
        window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
        window.localStorage.removeItem("Username");
        window.localStorage.removeItem("CurrentUser")

        this.username = null;
        this.authenticated = this.isAuthenticated();
    }

    // Saves the JWT authentication token and user details into the service and browser
    // storage for automatic retrieval after application or page reload
    saveTokenIntoBrowserStorage(token, user) {
        if (user !== null) {
            window.localStorage.setItem("Username", user.name);
            window.localStorage.setItem("CurrentUser", JSON.stringify(user))
        }
        if (token == null) {
            window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
            if (window.localStorage === window.sessionStorage) {
                window.localStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
            }
        } else {
            window.localStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token);
        }
    }

    // Retrieves the JWT authentication token and user details from the browser storage
    // into the service after application or page reload
    getTokenFromBrowserStorage() {
        let token = window.sessionStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        if (token == null) {
        // TODO try to find a token in localStorage and replicate into this session if found
            token = window.localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        }
        this._currentToken = token;
        return token;
    }

    isAuthenticated() {
        let token = window.localStorage.getItem(this.BROWSER_STORAGE_ITEM_NAME);
        return token != null;
    }
}