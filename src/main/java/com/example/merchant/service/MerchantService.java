package com.example.merchant.service;

import com.example.merchant.exception.ResourceNotFoundException;
import com.example.merchant.model.Merchant;
import com.example.merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository repository;

    @Autowired
    private KycService kycService;

    /**
     * Add merchant and run KYC stub; set status accordingly.
     */
    public Merchant addMerchant(Merchant merchant) {
        merchant.setStatus("PENDING");
        Merchant saved = repository.save(merchant);
        boolean ok = kycService.verifyIdentity(merchant.getName(), merchant.getEmail(), merchant.getBusinessName());
        saved.setStatus(ok ? "VERIFIED" : "REJECTED");
        return repository.save(saved);
    }

    public List<Merchant> getAllMerchants() {
        return repository.findAll();
    }

    public Optional<Merchant> getMerchantById(String id) {
        return repository.findById(id);
    }

    public List<Merchant> getMerchantsByCity(String city) {
        return repository.findByCityIgnoreCase(city);
    }

    public List<Merchant> searchByBusinessName(String businessName) {
        return repository.findByBusinessNameContainingIgnoreCase(businessName);
    }

    public Merchant updateMerchant(String id, Merchant updated) {
        return repository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setBusinessName(updated.getBusinessName());
            existing.setEmail(updated.getEmail());
            existing.setCity(updated.getCity());
            existing.setRating(updated.getRating());
            // Optionally re-run KYC
            boolean ok = kycService.verifyIdentity(existing.getName(), existing.getEmail(), existing.getBusinessName());
            existing.setStatus(ok ? "VERIFIED" : "REJECTED");
            return repository.save(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Merchant not found with id: " + id));
    }

    public void deleteMerchant(String id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Merchant not found with id: " + id);
        repository.deleteById(id);
    }
}
