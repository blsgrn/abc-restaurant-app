package com.balasegaran.AbcRestuarant.Repository;

import com.balasegaran.AbcRestuarant.Model.Offer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfferRepository extends MongoRepository<Offer, ObjectId> {
}
