package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "offers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
  @Id
  private ObjectId id;
  private String title;
  private String description;
  private double discount;
  private Date startDate;
  private Date endDate;
}
