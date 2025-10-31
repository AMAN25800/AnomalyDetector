package com.example.merchant.repository;

import com.example.merchant.model.TransactionEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<TransactionEvent, String> {}
