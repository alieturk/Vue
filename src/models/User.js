
export class User {
    email;
    password;

    constructor(email, password) {
        this.email = email;
        this.password = password;
    }

    static copyConstructor(user) {
        if (user == null) {
            return null;
        }
        return Object.assign(new User(), user);
    }
}