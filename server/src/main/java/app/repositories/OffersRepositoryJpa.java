package app.repositories;

import app.models.Offer;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Primary
@Transactional
@Component
public class OffersRepositoryJpa implements OffersRepository<Offer> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Offer> findByQuery(String jpqlName, Object... params) {

        TypedQuery<Offer> query =
            this.entityManager.createNamedQuery(jpqlName, Offer.class);

        // Set parameters
        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i]);
            query.setParameter((i+1), params[i]);
        }
        return query.getResultList();
    }

    @Override
    public List<Offer> findAll() {
        TypedQuery<Offer> query =
                this.entityManager.createQuery("Select o from Offer o", Offer.class);
        return query.getResultList();
    }

    @Override
    public Offer findById(long id) {
        return entityManager.find(Offer.class, id);
    }

    @Override
    public Offer save(Offer offer) {
        return entityManager.merge(offer);
    }

    @Override
    public Offer deleteById(long id) {
        Offer offer = findById(id);
        entityManager.remove(offer);
        return offer;
    }
    @Override
    public List<Offer> findAllForSale() {
        TypedQuery<Offer> query =
                this.entityManager.createQuery("Select o from Offer o where o.status = app.models.enums.Status.FOR_SALE", Offer.class);
        return query.getResultList();
    }

}
