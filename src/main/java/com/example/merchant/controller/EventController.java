package com.example.merchant.controller;

import com.example.merchant.model.TransactionEvent;
import com.example.merchant.service.EventService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // POST → Create a new event (e.g., transaction, onboarding, etc.)
    @PostMapping("/create")
    public ResponseEntity<TransactionEvent> createEvent(@RequestBody TransactionEvent event) {
        TransactionEvent savedEvent = eventService.saveEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    // GET → Fetch all events
    @GetMapping("/all")
    public ResponseEntity<List<TransactionEvent>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
}
