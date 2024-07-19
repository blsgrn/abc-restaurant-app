package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "queries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Query {
  @Id
  private ObjectId id;
  private String userId;
  private String query;
  private String response;
  private String status;
  private Date date;
}
