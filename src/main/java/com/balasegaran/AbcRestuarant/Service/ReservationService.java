package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Reservation;
import com.balasegaran.AbcRestuarant.Repository.ReservationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  public List<Reservation> getAllReservations() {
    return reservationRepository.findAll();
  }

  public Optional<Reservation> getReservationById(ObjectId id) {
    return reservationRepository.findById(id);
  }

  public List<Reservation> getReservationsByUserId(String userId) {
    return reservationRepository.findByUserId(userId);
  }

  public List<Reservation> getReservationsByRestaurantId(String restaurantId) {
    return reservationRepository.findByRestaurantId(restaurantId);
  }

  public Reservation createReservation(Reservation reservation) {
    return reservationRepository.save(reservation);
  }

  public Reservation updateReservation(ObjectId id, Reservation reservationDetails) {
    reservationDetails.setId(id);
    return reservationRepository.save(reservationDetails);
  }

  public void deleteReservation(ObjectId id) {
    reservationRepository.deleteById(id);
  }
}
