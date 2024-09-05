package com.balasegaran.AbcRestuarant.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateReservationReport {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<ReservationReport> generateDailyReport() {
    return generateReport("%Y-%m-%d"); // Group by day
  }

  public List<ReservationReport> generateWeeklyReport() {
    return generateWeeklyReportImpl(); // Group by week
  }

  public List<ReservationReport> generateMonthlyReport() {
    return generateReport("%Y-%m"); // Group by month
  }

  private List<ReservationReport> generateReport(String dateFormat) {
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.project()
            .andExpression("{$dateToString: { format: '" + dateFormat + "', date: '$date' }}").as("period"),
        Aggregation.group("period")
            .count().as("reservationCount"),
        Aggregation.project("reservationCount", "period"));

    AggregationResults<ReservationReport> result = mongoTemplate.aggregate(aggregation, "reservations",
        ReservationReport.class);

    return result.getMappedResults();
  }

  private List<ReservationReport> generateWeeklyReportImpl() {
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.project()
            .andExpression("{$isoWeekYear: '$date'}").as("year")
            .andExpression("{$isoWeek: '$date'}").as("week"),
        Aggregation.group("year", "week")
            .count().as("reservationCount"),
        Aggregation.project("reservationCount")
            .andExpression("concat('Week ', {$toString: '$week'}, ', ', {$toString: '$year'})").as("period"));

    AggregationResults<ReservationReport> result = mongoTemplate.aggregate(aggregation, "reservations",
        ReservationReport.class);

    return result.getMappedResults();
  }
}