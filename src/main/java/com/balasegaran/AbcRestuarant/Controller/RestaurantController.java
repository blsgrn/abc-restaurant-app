package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Restaurant;
import com.balasegaran.AbcRestuarant.Service.RestaurantService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping
  public List<Restaurant> getAllRestaurants() {
    return restaurantService.getAllRestaurants();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Restaurant> getRestaurantById(@PathVariable ObjectId id) {
    Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
    return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
    return restaurantService.createRestaurant(restaurant);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Restaurant> updateRestaurant(@PathVariable ObjectId id,
      @RequestBody Restaurant restaurantDetails) {
    Optional<Restaurant> updatedRestaurant = Optional
        .ofNullable(restaurantService.updateRestaurant(id, restaurantDetails));
    return updatedRestaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRestaurant(@PathVariable ObjectId id) {
    restaurantService.deleteRestaurant(id);
    return ResponseEntity.ok().build();
  }
}
