package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.User;
import com.balasegaran.AbcRestuarant.Security.JwtUtil;
import com.balasegaran.AbcRestuarant.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.balasegaran.AbcRestuarant.Model.LoginRequest;

import java.util.HashMap;
import java.util.Map;
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
  public ResponseEntity<User> getUserById(@PathVariable ObjectId id,
      @RequestHeader(value = "Authorization", required = false) String token) {
    // If token is provided, validate it
    if (token != null && token.startsWith("Bearer ")) {
      String jwtToken = token.substring(7); // Remove "Bearer " from the token
      String username = JwtUtil.extractClaims(jwtToken).getSubject();

      if (!JwtUtil.validateToken(jwtToken, username)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    }

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
  public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
    LOGGER.info("Login attempt with username: " + loginRequest.getUsername());
    try {
      Optional<User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
      if (user.isPresent()) {
        // Generate JWT token
        String token = JwtUtil.generateToken(loginRequest.getUsername());

        // Extract the user's id, name and role
        String role = user.get().getRole();
        String name = user.get().getName();
        String id = user.get().getId().toString();
        String email = user.get().getEmail();

        // Return the token and role as a JSON object
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", role);
        response.put("name", name);
        response.put("id", id);
        response.put("email", email);

        return ResponseEntity.ok(response);
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid username or password."));
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error during login", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Map.of("message", "An internal error occurred."));
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

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User userDetails) {
    try {
      Optional<User> existingUser = userService.getUserById(id);
      if (existingUser.isPresent()) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}
