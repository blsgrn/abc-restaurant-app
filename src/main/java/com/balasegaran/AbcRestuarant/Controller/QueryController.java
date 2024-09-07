package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Query;
import com.balasegaran.AbcRestuarant.Service.QueryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/queries")
public class QueryController {

  @Autowired
  private QueryService queryService;

  @GetMapping
  public List<Query> getQueries(@RequestParam(required = false) String userId) {
    if (userId != null) {
      return queryService.getQueriesByUserId(userId);
    } else {
      return queryService.getAllQueries();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Query> getQueryById(@PathVariable ObjectId id) {
    Optional<Query> query = queryService.getQueryById(id);
    return query.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Query createQuery(@RequestBody Query query) {
    return queryService.createQuery(query);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Query> updateQuery(@PathVariable ObjectId id, @RequestBody Query queryDetails) {
    Optional<Query> updatedQuery = Optional.ofNullable(queryService.updateQuery(id, queryDetails));
    return updatedQuery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteQuery(@PathVariable ObjectId id) {
    queryService.deleteQuery(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/analysis")
  public ResponseEntity<Map<String, Object>> getQueryAnalysisReport() {
    Map<String, Object> report = new HashMap<>();
    try {
      report.put("totalQueries", queryService.getTotalQueries());
      report.put("averageResponseTime", queryService.getAverageResponseTime());
      report.put("pendingQueries", queryService.getCountPendingQueries());
      report.put("resolvedQueries", queryService.getCountResolvedQueries());
      report.put("recentQueries", queryService.getRecentQueries(10)); // Adjust the number as needed
      report.put("statusBreakdown", queryService.getStatusBreakdown()); // Add this line

      return ResponseEntity.ok(report);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(null);
    }
  }
}
