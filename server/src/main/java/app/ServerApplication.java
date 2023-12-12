package app;

import app.models.Bids;
import app.models.Offer;
import app.models.User;
import app.repositories.BidsRepository;
import app.repositories.OffersRepository;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

import static app.models.enums.Status.NEW;


@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    @Qualifier("offersRepositoryJpa")
    @Autowired
    OffersRepository repository;

    @Autowired
    BidsRepository bidsRepository;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ServerApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) {
        System.out.println("Running commandline startup..");
        this.createInitialOffers();


    }

    public void createInitialOffers() {
        // Check weather the repository is empty
        List<Offer> offers = this.repository.findAll();

        if (offers.size() > 0) {
            return;
        }

        System.out.println("Configuring some initial offer data");
        User[] users = new User[4];

        users[0] = userRepository.save(new User(1L, "admin", "admin@gmail.com", "admin", "Registered user"));
        users[1] = userRepository.save(new User(2L, "james", "james@gmail.com", "james", "Registered user"));
        users[3] = userRepository.save(new User(3L, "trevor", "trevor@gmail.com", "trevor", "Registered user"));
//        users[4] = userRepository.save(new User(4L, "tyler", "tyler@gmail.com", "tyler", "Registered user"));

        for (int i = 0; i < 15; i++) {
            // Create and add a new random offer
            Offer offer = Offer.createRandomOffer();
            offer = (Offer) this.repository.save(offer);
            int random = new Random().nextInt(6);


            if (offer.getStatus() != NEW) {
                for (int j = 0; j < 2; j++) {
//                    int randomUser = new Random().nextInt(5);
                    Bids bids = this.bidsRepository.save(new Bids(offer.getHighestBid()+10, j, offer, users[j]));
                    offer.associateBid(bids);
                }
            }
        }
    }
}
