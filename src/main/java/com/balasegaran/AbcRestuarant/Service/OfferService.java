package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Offer;
import com.balasegaran.AbcRestuarant.Repository.OfferRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

  @Autowired
  private OfferRepository offerRepository;

  public List<Offer> getAllOffers() {
    return offerRepository.findAll();
  }

  public Optional<Offer> getOfferById(ObjectId id) {
    return offerRepository.findById(id);
  }

  public Offer createOffer(Offer offer) {
    return offerRepository.save(offer);
  }

  public Offer updateOffer(ObjectId id, Offer offerDetails) {
    offerDetails.setId(id);
    return offerRepository.save(offerDetails);
  }

  public void deleteOffer(ObjectId id) {
    offerRepository.deleteById(id);
  }
}
