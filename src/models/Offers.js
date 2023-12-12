export class Offer {
    id;
    title;
    status;
    description;
    sellDate;
    highestBid;
    static statusList = ["NEW", "FOR_SALE", "SOLD", "PAID", "DELIVERED", "CLOSED", "EXPIRED", "WITHDRAWN"];
    static descriptionList = ["As good as new", "In good condition", "Used", "Unique piece", "Pick up today only", "Delivery only", "Vintage"];
    static titleList = ["Car", "Watch", "Sofa", "Camera", "Desk", "Lamp", "Jacket", "Games", "Book", "Bike"];

    constructor(id, title, status, description, sellDate, highestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.highestBid = highestBid;
    }

    /**
     * creates and returns a new offer instance with realistic semi-random values for
     * all other attributes besides the given id
     * @param pId
     */
    static createSampleOffer(pId = 0) {
        // Create new offer instance
        let offer = new Offer(pId);

        // Random values for other attributes
        offer.status = (Offer.statusList)[Math.floor(Math.random() * 8)];
        offer.title = (Offer.titleList)[Math.floor(Math.random() * 10)];
        offer.description = (Offer.descriptionList)[Math.floor(Math.random() * 7)]

        let start = new Date(2022, 8, 1);
        let end = new Date(2022, 12, 1);
        offer.sellDate = new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));

        if (offer.status === (Offer.statusList)[0]) {
            offer.highestBid = 0;
        } else {
            offer.highestBid = Math.floor(Math.random() * 100) + 1;
        }
        return offer;
    }

    static setDate(offer, date) {
        offer.sellDate = date;
    }

    static copyConstructor(offer) {
        if (offer == null) {
            return null;
        }
        let copy = Object.assign(new Offer(), offer);
        copy.sellDate = new Date(offer.sellDate);
        return copy;
    }
}

Offer.prototype.toString = function () {
    return this.id + ", " + this.title + ", " + this.status + ", " + this.description +
        ", " + this.sellDate + ", highest bid: " + this.highestBid;
}

Offer.createSampleOffer();
export default class Offers {
}