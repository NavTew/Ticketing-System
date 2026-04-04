package com.NavyaLearning.inventoryservice.controller;

import com.NavyaLearning.inventoryservice.response.EventInventoryResponse;
import com.NavyaLearning.inventoryservice.response.VenueInventoryResponse;
import com.NavyaLearning.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory/events")
    @ResponseBody
    public List<EventInventoryResponse> inventoryGetAllEvents() {

        return inventoryService.getAllEvents();
    }

    @GetMapping("/inventory/venue/{venueId}")
    @ResponseBody
    public VenueInventoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId){
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("/inventory/event/{eventId}")
    @ResponseBody
    public EventInventoryResponse inventoryForEvent(@PathVariable("eventId") Long eventId){
        return inventoryService.getEventInventory(eventId);
    }



}
