package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Payment;
import com.balasegaran.AbcRestuarant.Service.PaymentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @GetMapping
  public List<Payment> getAllPayments() {
    return paymentService.getAllPayments();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Payment> getPaymentById(@PathVariable ObjectId id) {
    Optional<Payment> payment = paymentService.getPaymentById(id);
    return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Payment createPayment(@RequestBody Payment payment) {
    return paymentService.createPayment(payment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Payment> updatePayment(@PathVariable ObjectId id, @RequestBody Payment paymentDetails) {
    Optional<Payment> updatedPayment = Optional.ofNullable(paymentService.updatePayment(id, paymentDetails));
    return updatedPayment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePayment(@PathVariable ObjectId id) {
    paymentService.deletePayment(id);
    return ResponseEntity.ok().build();
  }
}
