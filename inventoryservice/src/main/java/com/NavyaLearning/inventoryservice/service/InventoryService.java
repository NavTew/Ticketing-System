package com.NavyaLearning.inventoryservice.service;

import com.NavyaLearning.inventoryservice.entity.Event;
import com.NavyaLearning.inventoryservice.entity.Venue;
import com.NavyaLearning.inventoryservice.repository.EventRepository;
import com.NavyaLearning.inventoryservice.repository.VenueRepository;
import com.NavyaLearning.inventoryservice.response.EventInventoryResponse;
import com.NavyaLearning.inventoryservice.response.VenueInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    public final EventRepository eventRepository;
    public final VenueRepository venueRepository;

    @Autowired
    public InventoryService(EventRepository eventRepository, VenueRepository venueRepository)
    {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public List<EventInventoryResponse> getAllEvents(){
        final List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventInventoryResponse.builder()
                .event(event.getName())
                .venue(event.getVenue())
                .capacity(event.getLeftCapacity())
                .build()).collect(Collectors.toList());

   }

    public VenueInventoryResponse getVenueInformation(Long venueId) {
        final Venue venue = venueRepository.findById(venueId).orElse(null);
        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();

    }

    public EventInventoryResponse getEventInventory(Long eventId) {
        final Event event = eventRepository.findById(eventId).orElse(null);
        return EventInventoryResponse.builder()
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .eventId(event.getId())
                .build();
    }
}
