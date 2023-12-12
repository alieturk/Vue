package app.repositories;

import java.util.List;

public interface OffersRepository<E> {

    List<E> findByQuery(String jpqlName, Object... params);

    List<E> findAll();

    E findById(long id);

    E save(E offer);

    E deleteById(long id);

    List<E> findAllForSale();
}
