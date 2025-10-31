package com.example.merchant.controller;

import com.example.merchant.model.Merchant;
import com.example.merchant.service.MerchantService;
import com.example.merchant.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/onboard")
@CrossOrigin(origins = "*")
public class OnboardController {

    @Autowired
    private MerchantService service;

    @PostMapping("/apply")
    public ResponseEntity<Merchant> apply(@RequestBody Merchant merchant) {
        Merchant saved = service.addMerchant(merchant);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Merchant>> list() {
        return ResponseEntity.ok(service.getAllMerchants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getById(@PathVariable String id) {
        Merchant m = service.getMerchantById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        return ResponseEntity.ok(m);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Merchant>> byCity(@PathVariable String city) {
        return ResponseEntity.ok(service.getMerchantsByCity(city));
    }

    @GetMapping("/search/{q}")
    public ResponseEntity<List<Merchant>> search(@PathVariable("q") String q) {
        return ResponseEntity.ok(service.searchByBusinessName(q));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> update(@PathVariable String id, @RequestBody Merchant merchant) {
        return ResponseEntity.ok(service.updateMerchant(id, merchant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.deleteMerchant(id);
        return ResponseEntity.ok("Deleted " + id);
    }
}
