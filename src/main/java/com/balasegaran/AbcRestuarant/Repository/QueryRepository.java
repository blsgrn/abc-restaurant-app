package com.balasegaran.AbcRestuarant.Repository;

import com.balasegaran.AbcRestuarant.Model.Query;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QueryRepository extends MongoRepository<Query, ObjectId> {
  List<Query> findByUserId(String userId);
}
