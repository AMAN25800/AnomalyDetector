package com.example.merchant.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AnomalyDetectorService {

    // Stores historical data for each merchant (avg amount, recent transactions)
    private final Map<String, List<Double>> merchantHistory = new ConcurrentHashMap<>();
    private final Map<String, List<Long>> merchantTimestamps = new ConcurrentHashMap<>();

    // Configurable thresholds
    private static final double GLOBAL_AMOUNT_LIMIT = 100000; // absolute max amount
    private static final int MAX_TRANSACTIONS_PER_MINUTE = 5; // frequency rule
    private static final double DEVIATION_FACTOR = 3.0; // anomaly if > 3x mean

    public boolean detectAnomaly(Map<String, Object> transaction) {
        try {
            String merchantId = transaction.get("merchantId").toString();
            double amount = Double.parseDouble(transaction.get("amount").toString());

            if (merchantId == null || merchantId.isEmpty()) return true;
            if (amount <= 0) return true; // invalid transaction

            // Global hard limit rule
            if (amount > GLOBAL_AMOUNT_LIMIT) return true;

            // Maintain transaction history
            merchantHistory.putIfAbsent(merchantId, new ArrayList<>());
            merchantTimestamps.putIfAbsent(merchantId, new ArrayList<>());

            List<Double> amounts = merchantHistory.get(merchantId);
            List<Long> timestamps = merchantTimestamps.get(merchantId);

            // Compute average for merchant
            double avg = amounts.isEmpty() ? amount : amounts.stream().mapToDouble(a -> a).average().orElse(amount);
            double deviation = Math.abs(amount - avg);

            // Rule 1: Detect if amount deviates too much from mean
            boolean amountAnomaly = (deviation > (avg * DEVIATION_FACTOR));

            // Rule 2: Frequency anomaly – too many transactions within 60 seconds
            long now = System.currentTimeMillis();
            timestamps.add(now);
            timestamps.removeIf(t -> (now - t) > 60_000); // keep only last 1 minute
            boolean frequencyAnomaly = (timestamps.size() > MAX_TRANSACTIONS_PER_MINUTE);

            // Update merchant’s history (keep only last 50 transactions)
            amounts.add(amount);
            if (amounts.size() > 50) amounts.remove(0);

            return amountAnomaly || frequencyAnomaly;

        } catch (Exception e) {
            System.err.println("Anomaly detection error: " + e.getMessage());
            return true; // treat invalid data as anomaly
        }
    }
}
