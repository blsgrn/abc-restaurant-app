package com.balasegaran.AbcRestuarant.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports/reservations")
public class ReservationReportController {

  @Autowired
  private GenerateReservationReport generateReservationReport;

  @GetMapping("/daily")
  public ResponseEntity<List<ReservationReport>> getDailyReport() {
    try {
      return ResponseEntity.ok(generateReservationReport.generateDailyReport());
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
  }

  @GetMapping("/weekly")
  public ResponseEntity<List<ReservationReport>> getWeeklyReport() {
    try {
      return ResponseEntity.ok(generateReservationReport.generateWeeklyReport());
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
  }

  @GetMapping("/monthly")
  public ResponseEntity<List<ReservationReport>> getMonthlyReport() {
    try {
      return ResponseEntity.ok(generateReservationReport.generateMonthlyReport());
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
  }
}
