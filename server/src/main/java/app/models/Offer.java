package app.models;

import app.models.enums.Status;
import app.views.View;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Entity
@NamedQueries({
        @NamedQuery(name="offer_find_by_status", query="SELECT o FROM Offer o WHERE o.status = ?1"),
        @NamedQuery(name="offer_find_by_title", query="SELECT o FROM Offer o WHERE o.title LIKE CONCAT('%', ?1,'%')"),
        @NamedQuery(name="offer_find_by_status_and_minBidValue", query="SELECT o FROM Offer o WHERE o.status = ?1 AND o.highestBid > ?2"),
})
public class Offer {
    @JsonView(View.Summary.class)
    @Id
    @GeneratedValue
    private long id;
    @JsonView(View.Summary.class)
    private String title;
    @JsonView(View.Summary.class)
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDate sellDate;
    private int highestBid;

    @OneToMany(mappedBy = "offer")
    @JsonManagedReference
    private List<Bids> bids = new ArrayList<Bids>();

    private static List<String> titles = Arrays.asList("Car", "Watch", "Sofa", "Camera", "Desk", "Lamp", "Jacket", "Games", "Book", "Bike");
    private static List<String> descriptions = Arrays.asList("As good as new", "In good condition", "Used", "Unique piece", "Pick up today only", "Delivery only", "Vintage");


    public Offer() {}

    public Offer(long id, String title, Status status, String description, LocalDate sellDate, int highestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.highestBid = highestBid;
    }

    public static Offer createRandomOffer() {
        Random random = new Random();
        String title = titles.get(random.nextInt(titles.size()));
        String description = descriptions.get(random.nextInt(descriptions.size()));
        int minDay = (int) LocalDate.of(2022, 8, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate sellDate = LocalDate.ofEpochDay(randomDay);
        int highestBid = random.nextInt(100 + 1 - 1) + 1;

        return new Offer(0, title, Status.randomStatus(), description, sellDate, highestBid);
    }

    public void setBids(List<Bids> bids) {
        this.bids = bids;
    }

    public List<Bids> getBids() {
        return bids;
    }

    public void addBid(Bids bid) {
        bids.add(bid);
    }

    public void removeBid(Bids bid) {
        bids.remove(bid);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public int getHighestBid() {
        return highestBid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    /**
     * @param newBid
     * @return
     */
    public boolean associateBid(Bids newBid) {
                if (newBid.getValue() > this.getHighestBid()) {
                    bids.add(newBid);
                    this.setHighestBid((int) newBid.getValue());
                    System.out.println("Highest bid: " + this.getHighestBid());
                    return true;
                }
            return false;
    }
        public boolean dissociateBid(Bids bid){
        if (bid != null){
            for (int i = 0; i < bids.size(); i++) {
                if (bid == bids.get(i)){
                    bids.remove(bid);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", sellDate=" + sellDate +
                ", highestBid=" + highestBid +
                '}';
    }
}
