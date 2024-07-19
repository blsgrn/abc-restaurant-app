package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Query;
import com.balasegaran.AbcRestuarant.Service.QueryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/queries")
public class QueryController {

  @Autowired
  private QueryService queryService;

  @GetMapping
  public List<Query> getAllQueries() {
    return queryService.getAllQueries();
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
}
