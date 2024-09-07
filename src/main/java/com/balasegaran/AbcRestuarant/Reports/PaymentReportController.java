package com.balasegaran.AbcRestuarant.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentReportController {

  @Autowired
  private GeneratePaymentReport generatePaymentReport;

  @GetMapping("/daily")
  public ResponseEntity<List<PaymentReport>> getDailyPayments() {
    List<PaymentReport> results = generatePaymentReport.getDailyPayments();
    return ResponseEntity.ok(results);
  }

  @GetMapping("/status")
  public ResponseEntity<List<PaymentStatusResult>> getStatusBreakdown() {
    List<PaymentStatusResult> results = generatePaymentReport.getStatusBreakdown();
    return ResponseEntity.ok(results);
  }
}
