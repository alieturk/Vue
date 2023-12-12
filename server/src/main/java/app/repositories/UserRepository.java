package app.repositories;


import app.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    public List<User> findByQuery(String jpqlName, Object... params) {

        TypedQuery<User> query =
                this.entityManager.createNamedQuery(jpqlName, User.class);

        // Set parameters
        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i]);
            query.setParameter((i+1), params[i]);
        }
        return query.getResultList();
    }

    public User save(User user) {
        return entityManager.merge(user);
    }

}
