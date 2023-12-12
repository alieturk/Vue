package app.repositories;

import app.models.Offer;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class OffersRepositoryMock implements OffersRepository {
    List<Offer> offers = new ArrayList<>();
    private static int offersCount = 0;


    public static Offer createSampleOffer() {
        offersCount++;
        Offer sampleOffer = new Offer().createRandomOffer();
        sampleOffer.setId(offersCount+2999);
        return sampleOffer;
    }

    public void addSampleOffers() {
        offers.add(createSampleOffer());
        offers.add(createSampleOffer());
        offers.add(createSampleOffer());
        offers.add(createSampleOffer());
        offers.add(createSampleOffer());
    }

    @Override
    public List findByQuery(String jpqlName, Object... params) {
        return null;
    }

    @Override
    public List<Offer> findAll() {
        return offers;
    }

    @Override
    public Offer save(Object object) {
        Offer offer = (Offer) object;
        for (int i = 0; i < offers.size(); i++) {
            if (offers.get(i).getId() == offer.getId()) {
                offers.set(i, offer);
                return offer;
            }
        }
        offers.add(offer);
        return offer;
    }

    @Override
    public Offer findById(long id) {
        for (Offer offer: offers) {
            if (offer.getId() == id) {
                return offer;
            }
        }
        return null;
    }

    public static int getOffersCount() {
        return offersCount;
    }

    public static void setOffersCount(int number) {
        offersCount = number;
    }

    @Override
    public Offer deleteById( long id) {
        Iterator<Offer> iterator = offers.iterator();
        while (iterator.hasNext()) {
            Offer offer = iterator.next();
            if (offer.getId() == id) {
                iterator.remove();
                return offer;
            }
        }
        return null;
    }

    @Override
    public List findAllForSale() {
        return null;
    }
}