package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.User;
import com.balasegaran.AbcRestuarant.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(ObjectId id) {
    return userRepository.findById(id);
  }

  public Optional<User> getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(ObjectId id, User userDetails) {
    userDetails.setId(id);
    return userRepository.save(userDetails);
  }

  public void deleteUser(ObjectId id) {
    userRepository.deleteById(id);
  }
}
