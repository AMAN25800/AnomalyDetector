package com.example.merchant.service;

import com.example.merchant.model.TransactionEvent;
import com.example.merchant.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public TransactionEvent saveEvent(TransactionEvent event) {
        event.setTimestamp(System.currentTimeMillis());
        return eventRepository.save(event);
    }

    public List<TransactionEvent> getAllEvents() {
        return eventRepository.findAll();
    }
}
