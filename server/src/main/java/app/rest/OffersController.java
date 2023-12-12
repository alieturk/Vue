package app.rest;

import app.Exceptions.BadRequest;
import app.Exceptions.PreConditionFailed;
import app.Exceptions.ResourceNotFound;

import app.models.Bids;
import app.models.Offer;
import app.models.enums.Status;
import app.repositories.BidsRepositoryJpa;
import app.repositories.OffersRepository;
import app.repositories.OffersRepositoryMock;
import app.views.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OffersController {

    @Autowired
    private OffersRepository<Offer> repository;

    @Autowired
    private BidsRepositoryJpa bidsRepositoryJpa;


    @GetMapping("/offers")
    public List<Offer> getOffersByStatus(@RequestParam(required = false, name = "status") String statusText
                                       , @RequestParam(required = false, name = "title") String title
                                       , @RequestParam(required = false, name = "minBidValue") String minBidValueText) {
        // If there are no given request parameters, return all offers
        if (statusText == null && title == null && minBidValueText == null) {
            return repository.findAll();
        }

        List<Offer> allOffers = new ArrayList<>();
        Status status;
        int minBidValue;

        // Find by status
        if (statusText != null) {
            try {
                // Check if the status is valid
                status = Status.valueOf(statusText);
            } catch (Exception e) {
                throw new BadRequest("The status: " + statusText + " is not valid and should be like: " + Arrays.toString(Status.values()));
            }

            // Find status and minBidValue
            if (minBidValueText != null) {
                try {
                    // Check if the status is valid
                    minBidValue = Integer.parseInt(minBidValueText);
                } catch (Exception e) {
                    throw new BadRequest("The minBidValue should be a number");
                }
                allOffers.addAll(repository.findByQuery("offer_find_by_status_and_minBidValue", status, minBidValue));
            } else {
                allOffers.addAll(repository.findByQuery("offer_find_by_status", status));
            }
        } else if (minBidValueText != null) {
            throw new BadRequest("Cannot handle your combination of request parameters title=, status=, and minBidValue=");
        }

        // Find by title
        if (title != null) {
            allOffers.addAll(repository.findByQuery("offer_find_by_title", title));
        }
        System.out.println(allOffers);
        return allOffers;
    }

    @GetMapping("offers/{id}")
    public Offer retrieveOffer(@PathVariable long id) {
        Offer retrievedOffer = repository.findById(id);
        if (retrievedOffer == null) {
            throw new ResourceNotFound("There is no offer with an id of " + id);
        }
        return retrievedOffer;
    }

    @GetMapping("offers/summary")
    @JsonView(View.Summary.class)
    public List<Offer> getOffersSummary() {
        return repository.findAll();
    }

    @PostMapping("/offers")
    public ResponseEntity<Object> createOffer(@RequestBody Offer offer) throws PreConditionFailed {
        if (repository.findById(offer.getId()) != null) {
            throw new PreConditionFailed("The id should be changed, this id already belongs to another offer.");
        }

        if (offer.getId() == 0) {
            int newId = OffersRepositoryMock.getOffersCount();
            OffersRepositoryMock.setOffersCount(newId+1);
            offer.setId(OffersRepositoryMock.getOffersCount()+2999);
        }
        Offer createdOffer = repository.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(offer.getId()).toUri();
        return ResponseEntity.created(location).body(createdOffer);
    }

    @PutMapping("offers/{id}")
    public ResponseEntity<Object> putOffer(@RequestBody Offer changedOffer, @PathVariable long id) throws PreConditionFailed {
        Offer oldOffer = repository.findById(id);
        if (oldOffer == null) {
            throw new ResourceNotFound("There is no offer with an id of " + id);
        }
        if (changedOffer.getId() != id) {
            throw new PreConditionFailed("The id should be changed back to the id of the offer in the request body");

        }
        Offer savedOffer = repository.save(changedOffer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(changedOffer.getId()).toUri();
        return ResponseEntity.created(location).body(savedOffer);
    }

    @DeleteMapping("offers/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer deletedOffer = repository.deleteById(id);

        if (deletedOffer == null) {
            throw new ResourceNotFound("There is no offer with an id of " + id);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(deletedOffer.getId()).toUri();
        return ResponseEntity.created(location).body(deletedOffer);
    }

    @PostMapping("/offers/{offerId}/bids")
    public ResponseEntity<Bids> addBids(@PathVariable long offerId, @RequestBody Bids newBid){
        Offer offer = this.repository.findById(offerId);

        if (offer.getHighestBid() > newBid.getValue()) {
            throw new PreConditionFailed(
                    String.format("The value of the bid should be higher than the last value : %d new value is: %f", offer.getHighestBid(), newBid.getValue()));

        }
        if (offer.getStatus()!= Status.FOR_SALE) {
            throw new PreConditionFailed(
                    String.format("The offer is not for sale"));

        }
                Bids bid = bidsRepositoryJpa.save(newBid);
                if (!bid.associateOffer(offer)){
                    throw new PreConditionFailed(
                            String.format("Cannot associateOffer bid '%s' with offer with id = %d", bid.getId(), offerId));
                }

                if (!offer.associateBid(bid)){
                    throw new PreConditionFailed(
                            String.format("Cannot associateBid '%s' with offer with id = %d", bid.getId(), offerId));
                }
                repository.save(offer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bid.getId()).toUri();
        return ResponseEntity.created(location).body(bid);
    }

    @GetMapping("/offers/myBids")
    public List<Offer> getAllForSale(){
        return this.repository.findAllForSale();
    }
}
