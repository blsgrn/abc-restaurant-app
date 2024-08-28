package com.balasegaran.AbcRestuarant.Model;

import com.balasegaran.AbcRestuarant.Config.ObjectIdSerializer; // Import the serializer
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospitality {
  @Id
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;
  private String name;
  private String description;
  private double price;
  private String category;
  private String imageUrl;
}
