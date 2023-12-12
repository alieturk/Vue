package app.repositories;

import app.models.Bids;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Primary
@Transactional
public class BidsRepositoryJpa implements BidsRepository{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Bids> findAll() {
        TypedQuery<Bids> query =
                this.entityManager.createQuery("Select o from Bids o", Bids.class);
        return query.getResultList();
    }

    @Override
    public Bids findById(long id) {
        return entityManager.find(Bids.class, id);
    }

    @Override
    public Bids save(Bids bid) {
        return entityManager.merge(bid);
    }

    @Override
    public Bids deleteById(long id) {
        Bids bid = findById(id);
        entityManager.remove(bid);
        return bid;
    }
}
