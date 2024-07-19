package com.balasegaran.AbcRestuarant.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private ObjectId id;
    private String userId;
    private String restaurantId;
    private String serviceId;
    private Date date;
    private String time;
    private String status;
    private String type;
}
