import fetchIntercept from 'fetch-intercept';

export class FetchInterceptor {
    static theInstance;
    static session;
    router;
    unregister;

    constructor(session, router) {
        FetchInterceptor.theInstance = this;
        FetchInterceptor.session = session;
        this.router = router;
        this.unregister = fetchIntercept.register(this);
        let token = FetchInterceptor.session.getTokenFromBrowserStorage();
        console.log("token = " + token)
    }

    request(url, options) {
        let token = FetchInterceptor.session.getTokenFromBrowserStorage();
        if (token == null) {
        // No change
        return [url, options];
        } else if (options == null) {
            // The authorisation header is the only custom option
            return [url, { headers: { Authorization: token }}]
        } else {
            // Add authorization header to other options
            let newOptions = { ...options };
            newOptions.headers = {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
            return [url, newOptions];
        }
    }

    response(response) {
        let token = FetchInterceptor.session.getTokenFromBrowserStorage();
        console.log(token)

        // Trap some specific http error responses
        if (response.status === 401) {
            console.log("error 401")
            this.$router.push("/sign-in")
        }
        if (response.status >= 400 && response.status < 600) {
            // FetchInterceptor.theInstance.handleErrorInResponse(response);
        }
        return response;
    }

    responseError(error) {
        return Promise.reject(error);
    }

    requestError(error) {
        return Promise.reject(error);
    }
}