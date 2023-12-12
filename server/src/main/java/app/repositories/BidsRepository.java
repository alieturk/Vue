package app.repositories;

import app.models.Bids;

import java.util.List;

public interface BidsRepository {

     List<Bids> findAll();

    Bids findById(long id);

    Bids save(Bids bid);

    Bids deleteById(long id);
}
