package com.example.merchant.controller;

import com.example.merchant.service.AnomalyDetectorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api/anomaly")
@CrossOrigin(origins = "*")
public class AnomalyController {

    private final AnomalyDetectorService anomalyDetectorService;

    public AnomalyController(AnomalyDetectorService anomalyDetectorService) {
        this.anomalyDetectorService = anomalyDetectorService;
    }

    @PostMapping("/detect")
    public ResponseEntity<?> detectAnomaly(@RequestBody Map<String, Object> transaction) {
        boolean isAnomalous = anomalyDetectorService.detectAnomaly(transaction);
        return ResponseEntity.ok(Map.of(
            "anomalyDetected", isAnomalous,
            "message", isAnomalous ? "Anomaly detected in transaction" : "Transaction looks normal"
        ));
    }
}
