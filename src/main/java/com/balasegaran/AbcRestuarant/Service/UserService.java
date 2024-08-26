package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.User;
import com.balasegaran.AbcRestuarant.Repository.UserRepository;
import com.balasegaran.AbcRestuarant.Validation.UserRegistration;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class UserService {

  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private UserRepository userRepository;

  public boolean isUsernameTaken(String username) {
    return userRepository.findByUsername(username).isPresent();
  }

  public boolean isEmailTaken(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

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
    if (isUsernameTaken(user.getUsername())) {
      throw new IllegalArgumentException("Username is already taken.");
    }

    if (isEmailTaken(user.getEmail())) {
      throw new IllegalArgumentException("Email is already registered.");
    }

    validateUser(user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User updateUser(ObjectId id, User userDetails) {
    userDetails.setId(id);
    return userRepository.save(userDetails);
  }

  public void deleteUser(ObjectId id) {
    userRepository.deleteById(id);
  }

  public void validateUser(User user) {
    if (!UserRegistration.isValidUsername(user.getUsername())) {
      throw new IllegalArgumentException("Invalid username: Must be alphanumeric and can include underscores.");
    }
    if (!UserRegistration.isValidPassword(user.getPassword())) {
      throw new IllegalArgumentException(
          "Invalid password: Must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, one digit, and one special character.");
    }

    if (!UserRegistration.isValidEmail(user.getEmail())) {
      throw new IllegalArgumentException("Invalid email address: Must be a valid email format.");
    }
  }

  public Optional<User> authenticate(String username, String password) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
      return user;
    }
    return Optional.empty();
  }

}
