package com.balasegaran.AbcRestuarant.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratePaymentReport {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<PaymentReport> generatePaymentReport() {
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.project()
            .andExpression("{$dateToString: { format: '%Y-%m', date: '$date' }}").as("period"),
        Aggregation.group("period")
            .sum("amount").as("totalAmount")
            .count().as("paymentCount"),
        Aggregation.project("totalAmount", "paymentCount")
            .and("period").previousOperation());

    AggregationResults<PaymentReport> result = mongoTemplate.aggregate(aggregation, "payments", PaymentReport.class);

    return result.getMappedResults();
  }
}
