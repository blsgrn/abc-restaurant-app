package com.balasegaran.AbcRestuarant.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class PaymentReportController {

  @Autowired
  private GeneratePaymentReport generatePaymentReport;

  @GetMapping("/reports/payments")
  public ResponseEntity<List<PaymentReport>> getPaymentReport() {
    List<PaymentReport> report = generatePaymentReport.generatePaymentReport();
    return ResponseEntity.ok(report);
  }
}
