package com.balasegaran.AbcRestuarant.Repository;

import com.balasegaran.AbcRestuarant.Model.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, ObjectId> {
  List<Reservation> findByUserId(String userId);

  List<Reservation> findByRestaurantId(String restaurantId);
}
