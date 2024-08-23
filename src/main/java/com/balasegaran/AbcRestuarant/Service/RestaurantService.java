package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Restaurant;
import com.balasegaran.AbcRestuarant.Repository.RestaurantRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class RestaurantService {

  @Autowired
  private RestaurantRepository restaurantRepository;

  public List<Restaurant> getAllRestaurants() {
    return restaurantRepository.findAll();
  }

  public Optional<Restaurant> getRestaurantById(ObjectId id) {
    return restaurantRepository.findById(id);
  }

  public Restaurant createRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  public Restaurant updateRestaurant(ObjectId id, Restaurant restaurantDetails) {
    restaurantDetails.setId(id);
    return restaurantRepository.save(restaurantDetails);
  }

  public void deleteRestaurant(ObjectId id) {
    restaurantRepository.deleteById(id);
  }
}
