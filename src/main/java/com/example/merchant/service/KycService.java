package com.example.merchant.service;

import org.springframework.stereotype.Service;

/**
 * Simple KYC stub for demo.
 * Replace with real KYC provider integration in production.
 */
@Service
public class KycService {

    /**
     * Very basic verification rules (demo only):
     * - email must contain '@' and not 'test'
     * - business name shouldn't be empty
     */
    public boolean verifyIdentity(String name, String email, String businessName) {
        if (email == null || !email.contains("@")) return false;
        if (email.toLowerCase().contains("test")) return false;
        return businessName != null && !businessName.trim().isEmpty();
    }
}
