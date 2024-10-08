package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  private ObjectId id;
  private String username;
  private String password;
  private String role;
  private String name;
  private String email;
  private boolean subscribe;
  private String contactNumber;
}