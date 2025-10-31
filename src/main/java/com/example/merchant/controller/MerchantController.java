package com.example.merchant.controller;

import com.example.merchant.model.Merchant;
import com.example.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles all CRUD operations for merchants:
 *  - Add merchant
 *  - Get all merchants
 *  - Get merchant by ID
 *  - Update merchant
 *  - Delete merchant
 */
@RestController
@RequestMapping("/api/merchants")
@CrossOrigin(origins = "*") // Allow frontend (React) access
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    // ✅ Create a new merchant
    @PostMapping
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        Merchant savedMerchant = merchantService.addMerchant(merchant);
        return ResponseEntity.ok(savedMerchant);
    }

    // ✅ Get all merchants
    @GetMapping
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }

    // ✅ Get a single merchant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable String id) {
        return merchantService.getMerchantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update merchant details
    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable String id, @RequestBody Merchant merchant) {
        Merchant updatedMerchant = merchantService.updateMerchant(id, merchant);
        return ResponseEntity.ok(updatedMerchant);
    }

    // ✅ Delete merchant
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable String id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.ok("Merchant deleted successfully");
    }
}
