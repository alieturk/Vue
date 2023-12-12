package app.models;
import app.views.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;


@Entity
@Table(name = "`Bids`")
public class Bids {
    @Id
    @GeneratedValue
    @JsonView(View.Summary.class)
    private Long id = 0L;

    @Column(name = "`values`")
    private double values;

    @ManyToOne
    @JsonBackReference
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bids(double values, long id, Offer offer, User user) {
        this.id = id;
        this.values = values;
        this.offer = offer;
        this.user = user;
    }

    public Bids() {}

    public double getValue() {
        return values;
    }

    public void setValue(double value) {
        this.values = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOffer(Offer offer) {this.offer = offer;}

    public Offer getOffer() {return offer;}

    public boolean associateOffer(Offer offer) {
        if (this.offer != offer){
            this.offer = offer;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Bids{" +
                "id=" + id +
                ", value=" + values +
                '}';
    }
}
