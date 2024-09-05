package com.balasegaran.AbcRestuarant.Reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationReport {
  private String period; // For daily and monthly reports
  private long reservationCount;
  private int year; // For weekly report
  private int week; // For weekly report
}
