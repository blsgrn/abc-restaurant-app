package com.balasegaran.AbcRestuarant.Repository;

import com.balasegaran.AbcRestuarant.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);
}
