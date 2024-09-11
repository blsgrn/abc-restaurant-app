package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Query;
import com.balasegaran.AbcRestuarant.Repository.QueryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class QueryService {

  @Autowired
  private QueryRepository queryRepository;

  public List<Query> getAllQueries() {
    return queryRepository.findAll();
  }

  public List<Query> getQueriesByUserId(String userId) {
    return queryRepository.findByUserId(userId);
  }

  public Optional<Query> getQueryById(ObjectId id) {
    return queryRepository.findById(id);
  }

  public Query createQuery(Query query) {
    return queryRepository.save(query);
  }

  public Query updateQuery(ObjectId id, Query queryDetails) {
    queryDetails.setId(id);
    return queryRepository.save(queryDetails);
  }

  public void deleteQuery(ObjectId id) {
    queryRepository.deleteById(id);
  }

  public long getTotalQueries() {
    return queryRepository.count();
  }

  public double getAverageResponseTime() {
    // Get all queries with status "Closed"
    List<Query> respondedQueries = queryRepository.findByStatus("Closed");

    long totalResponseTime = 0;
    int count = 0;

    for (Query query : respondedQueries) {

      if (query.getResponseDate() != null) {

        long responseTimeMillis = query.getResponseDate().getTime() - query.getDate().getTime();

        long responseTimeMinutes = responseTimeMillis / (1000 * 60);

        totalResponseTime += responseTimeMinutes;
        count++;
      }
    }

    return count == 0 ? 0 : totalResponseTime / (double) count;
  }

  public long getCountPendingQueries() {
    return queryRepository.countByStatus("Open");
  }

  public long getCountResolvedQueries() {
    return queryRepository.countByStatus("Closed");
  }

  public List<Query> getRecentQueries(int limit) {
    return queryRepository.findTopNByOrderByDateDesc(limit);
  }

  public List<Map<String, Object>> getStatusBreakdown() {
    List<Query> allQueries = queryRepository.findAll();
    Map<String, Long> statusCount = allQueries.stream()
        .collect(Collectors.groupingBy(Query::getStatus, Collectors.counting()));

    List<Map<String, Object>> breakdown = new ArrayList<>();
    for (Map.Entry<String, Long> entry : statusCount.entrySet()) {
      Map<String, Object> status = new HashMap<>();
      status.put("label", entry.getKey());
      status.put("value", entry.getValue());
      breakdown.add(status);
    }

    return breakdown;
  }
}
