package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gallery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gallery {
  @Id
  private ObjectId id;
  private String restaurantId;
  private String imageUrl;
  private String description;
}
