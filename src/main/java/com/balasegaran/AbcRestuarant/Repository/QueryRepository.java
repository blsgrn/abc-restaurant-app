package com.balasegaran.AbcRestuarant.Repository;

import com.balasegaran.AbcRestuarant.Model.Query;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface QueryRepository extends MongoRepository<Query, ObjectId> {

  List<Query> findByUserId(String userId);

  List<Query> findByStatus(String status);

  long countByStatus(String status);

  default List<Query> findTopNByOrderByDateDesc(int limit) {
    return findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("date")))).getContent();
  }
}
