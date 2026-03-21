package com.NavyaLearning.bookingservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private String event;
    private Long capacity;
    private VenueResponse venue;
    private Long eventId;
    private BigDecimal ticketPrice;
}
