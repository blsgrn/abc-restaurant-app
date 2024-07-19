package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
  @Id
  private ObjectId id;
  private String name;
  private Location location;
  private String contactNumber;
  private String email;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Location {
    private String address;
    private String city;
    private String postalCode;
    private String country;
  }
}
