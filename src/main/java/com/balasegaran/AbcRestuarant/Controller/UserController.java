package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.User;
import com.balasegaran.AbcRestuarant.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable ObjectId id) {
    Optional<User> user = userService.getUserById(id);
    return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody User user) {
    try {
      User savedUser = userService.createUser(user);
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      LOGGER.log(Level.SEVERE, "Error creating user", e);
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
    Optional<User> user = userService.authenticate(username, password);
    if (user.isPresent()) {
      // Here you could generate a JWT token or manage session
      return ResponseEntity.ok("Login successful!");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User userDetails) {
    Optional<User> existingUser = userService.getUserById(id);
    if (existingUser.isPresent()) {
      User updatedUser = userService.updateUser(id, userDetails);
      return ResponseEntity.ok(updatedUser);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
    Optional<User> existingUser = userService.getUserById(id);
    if (existingUser.isPresent()) {
      userService.deleteUser(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
