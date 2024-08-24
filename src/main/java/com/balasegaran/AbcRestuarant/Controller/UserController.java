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

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

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
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User userDetails) {
    Optional<User> updatedUser = Optional.ofNullable(userService.updateUser(id, userDetails));
    return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}