package com.example.merchant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "merchants")
public class Merchant {
    @Id
    private String id;
    private String name;
    private String businessName;
    private String email;
    private String city;
    private double rating;     // 0.0 - 5.0
    private String status;     // PENDING, VERIFIED, REJECTED
}
