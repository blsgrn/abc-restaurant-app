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
    private String userName;
    private String userEmail;
    private String restaurantId;
    private String serviceId;
    private double serviceCharge;
    private Date date;
    private String time;
    private Integer noOfGuests;
    private String specialRequests;
    private String type;
    // Fields with default values that can updated by staff in frontend
    private double diningPrice = 4800.0;
    private double deliveryPrice = 800.0;
    private double specialRequestCharge = 1000.0;
    private String status = "Pending";
}
