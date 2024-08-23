package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Query;
import com.balasegaran.AbcRestuarant.Repository.QueryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class QueryService {

  @Autowired
  private QueryRepository queryRepository;

  public List<Query> getAllQueries() {
    return queryRepository.findAll();
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
}
