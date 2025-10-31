package com.example.merchant.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api/kyc")
public class KycController {

    @PostMapping("/verify")
    public ResponseEntity<?> verifyKyc(@RequestBody Map<String, String> data) {
        String merchantId = data.get("merchantId");
        // You can add service call here to validate merchant
        boolean verified = merchantId != null && merchantId.startsWith("M-");

        return ResponseEntity.ok(Map.of(
            "merchantId", merchantId,
            "verified", verified,
            "message", verified ? "KYC verified successfully" : "Invalid Merchant ID"
        ));
    }
}
