package com.example.merchant.repository;

import com.example.merchant.model.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MerchantRepository extends MongoRepository<Merchant, String> {
    List<Merchant> findByCityIgnoreCase(String city);
    List<Merchant> findByBusinessNameContainingIgnoreCase(String businessName);
}
