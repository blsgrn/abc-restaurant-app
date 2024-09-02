package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Payment;
import com.balasegaran.AbcRestuarant.Model.Reservation;
import com.balasegaran.AbcRestuarant.Service.PaymentService;
import com.balasegaran.AbcRestuarant.Service.ReservationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private PaymentService paymentService;

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
  public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
    // Create the reservation
    Reservation createdReservation = reservationService.createReservation(reservation);

    // Return the created reservation without creating a payment
    return ResponseEntity.ok(createdReservation);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Reservation> updateReservation(@PathVariable ObjectId id,
      @RequestBody Reservation reservationDetails) {
    Optional<Reservation> updatedReservationOpt = Optional
        .ofNullable(reservationService.updateReservation(id, reservationDetails));

    if (updatedReservationOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Reservation updatedReservation = updatedReservationOpt.get();

    // Calculate the payment amount
    double totalAmount = calculateTotalAmount(updatedReservation);

    // Create or update the payment
    Payment payment = new Payment();
    payment.setReservationId(updatedReservation.getId().toString());
    payment.setAmount(totalAmount);
    payment.setDate(new Date());
    payment.setStatus("Pending");

    paymentService.createPayment(payment);

    return ResponseEntity.ok(updatedReservation);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable ObjectId id) {
    reservationService.deleteReservation(id);
    return ResponseEntity.ok().build();
  }

  private double calculateTotalAmount(Reservation reservation) {
    double basePrice = "Dining".equalsIgnoreCase(reservation.getType())
        ? reservation.getDiningPrice()
        : reservation.getDeliveryPrice();

    double totalAmount = basePrice
        + reservation.getServiceCharge()
        + reservation.getSpecialRequestCharge();

    return totalAmount;
  }
}
