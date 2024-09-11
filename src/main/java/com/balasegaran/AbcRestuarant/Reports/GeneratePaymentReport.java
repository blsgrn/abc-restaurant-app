package com.balasegaran.AbcRestuarant.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratePaymentReport {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<PaymentReport> getDailyPayments() {
        Aggregation aggregation = Aggregation.newAggregation(

                Aggregation.project()
                        .and(DateOperators.DateToString.dateOf("date").toString("%Y-%m-%d")).as("formattedDate")
                        .and("amount").as("amount"),

                Aggregation.group("formattedDate")
                        .sum("amount").as("totalAmount"),

                Aggregation.project("totalAmount")
                        .and("_id").as("date"),

                Aggregation.sort(Sort.by(Sort.Order.asc("date"))));

        AggregationResults<PaymentReport> results = mongoTemplate.aggregate(aggregation, "payments",
                PaymentReport.class);

        List<PaymentReport> mappedResults = results.getMappedResults();
        mappedResults.forEach(
                report -> System.out.println("Date: " + ", Total Amount: " + report.getTotalAmount()));

        return mappedResults;
    }

    public List<PaymentStatusResult> getStatusBreakdown() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("status").count().as("count"),
                Aggregation.sort(Sort.by(Sort.Order.asc("_id"))));

        AggregationResults<PaymentStatusResult> results = mongoTemplate.aggregate(aggregation, "payments",
                PaymentStatusResult.class);
        return results.getMappedResults();
    }
}
