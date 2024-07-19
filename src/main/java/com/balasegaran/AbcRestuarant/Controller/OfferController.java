package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Offer;
import com.balasegaran.AbcRestuarant.Service.OfferService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {

  @Autowired
  private OfferService offerService;

  @GetMapping
  public List<Offer> getAllOffers() {
    return offerService.getAllOffers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Offer> getOfferById(@PathVariable ObjectId id) {
    Optional<Offer> offer = offerService.getOfferById(id);
    return offer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Offer createOffer(@RequestBody Offer offer) {
    return offerService.createOffer(offer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Offer> updateOffer(@PathVariable ObjectId id, @RequestBody Offer offerDetails) {
    Optional<Offer> updatedOffer = Optional.ofNullable(offerService.updateOffer(id, offerDetails));
    return updatedOffer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOffer(@PathVariable ObjectId id) {
    offerService.deleteOffer(id);
    return ResponseEntity.ok().build();
  }
}
