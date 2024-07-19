package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Payment;
import com.balasegaran.AbcRestuarant.Repository.PaymentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  public List<Payment> getAllPayments() {
    return paymentRepository.findAll();
  }

  public Optional<Payment> getPaymentById(ObjectId id) {
    return paymentRepository.findById(id);
  }

  public Payment createPayment(Payment payment) {
    return paymentRepository.save(payment);
  }

  public Payment updatePayment(ObjectId id, Payment paymentDetails) {
    paymentDetails.setId(id);
    return paymentRepository.save(paymentDetails);
  }

  public void deletePayment(ObjectId id) {
    paymentRepository.deleteById(id);
  }
}
