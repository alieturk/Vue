
export class Bids{
    id
    value

    user

    constructor(id, value, user) {
        this.id = id;
        this.value = value;
        this.user = user;
    }
    static copyConstructor(bid) {
        if (bid == null) {
            return null;
        }
        return Object.assign(new Bids(0), bid
        );
    }
}