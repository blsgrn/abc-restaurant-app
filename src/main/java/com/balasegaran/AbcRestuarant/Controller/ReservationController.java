package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Reservation;
import com.balasegaran.AbcRestuarant.Service.ReservationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @GetMapping
  public List<Reservation> getAllReservations() {
    return reservationService.getAllReservations();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Reservation> getReservationById(@PathVariable ObjectId id) {
    Optional<Reservation> reservation = reservationService.getReservationById(id);
    return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public List<Reservation> getReservationsByUserId(@PathVariable String userId) {
    return reservationService.getReservationsByUserId(userId);
  }

  @GetMapping("/restaurant/{restaurantId}")
  public List<Reservation> getReservationsByRestaurantId(@PathVariable String restaurantId) {
    return reservationService.getReservationsByRestaurantId(restaurantId);
  }

  @PostMapping
  public Reservation createReservation(@RequestBody Reservation reservation) {
    return reservationService.createReservation(reservation);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Reservation> updateReservation(@PathVariable ObjectId id,
      @RequestBody Reservation reservationDetails) {
    Optional<Reservation> updatedReservation = Optional
        .ofNullable(reservationService.updateReservation(id, reservationDetails));
    return updatedReservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable ObjectId id) {
    reservationService.deleteReservation(id);
    return ResponseEntity.ok().build();
  }
}
