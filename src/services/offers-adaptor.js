import {Offer} from '@/models/Offers'

export class OffersAdaptor {
    resourcesURL;

    constructor(resourcesURL) {
        this.resourcesURL = resourcesURL;
        console.log("Created OffersAdaptor for " + resourcesURL);
    }

    async fetchJson(url, options = null) {
        let response = await fetch(url, options)
        if (response.ok) {
            return await response.json();
        } else {
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async asyncFindAll() /* :Promise Offer[] */ {
        const offerData = await this.fetchJson(this.resourcesURL);
        console.log(offerData)
        return offerData?.map(Offer.copyConstructor);
    }


    async asyncFindById(id) /* :Promise<Offer> */ {
        console.log('OffersAdaptor.asyncFindById(' + id + ')...');
        const offerData = await this.fetchJson(this.resourcesURL + "/" + id);
        if (offerData != null) {
            return Offer.copyConstructor(offerData);
        }
        return null;
    }

    async asyncSave(offer, changedOffer) /* :Promise<Offer> */ {
        if (changedOffer !== null) {
            console.log("PUT")
            return this.fetchJson(this.resourcesURL + "/" + changedOffer.id,
                {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(changedOffer)
                });
        } else {
            console.log("POST")
            return this.fetchJson(this.resourcesURL,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(offer)
                });
        }
    }

    async asyncDeleteById(id) /* :Promise<void> */ {
        return this.fetchJson(this.resourcesURL + "/" + id,
            {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            });
    }
    async asyncFindAllForSale() /* :Promise Offer[] */ {
        const offerData = await this.fetchJson(this.resourcesURL + "/myBids");
        console.log(offerData)
        return offerData?.map(Offer.copyConstructor);
    }

    async asyncPostBid(id, data){
        return this.fetchJson(this.resourcesURL + "/" + id + "/bids" ,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
    }
}