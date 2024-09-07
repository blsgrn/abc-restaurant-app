package com.balasegaran.AbcRestuarant.Reports;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReport {
  private String _id;
  private double totalAmount;
}
