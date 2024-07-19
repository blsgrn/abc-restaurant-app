package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "promotions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
  @Id
  private ObjectId id;
  private String title;
  private String description;
  private Date startDate;
  private Date endDate;
}
