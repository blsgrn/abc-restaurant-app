package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospitality {
  @Id
  private ObjectId id;
  private String name;
  private String description;
  private double price;
  private String category;
}
