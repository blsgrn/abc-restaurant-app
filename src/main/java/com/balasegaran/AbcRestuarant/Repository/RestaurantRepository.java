package com.balasegaran.AbcRestuarant.Repository;

import com.balasegaran.AbcRestuarant.Model.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, ObjectId> {
}
