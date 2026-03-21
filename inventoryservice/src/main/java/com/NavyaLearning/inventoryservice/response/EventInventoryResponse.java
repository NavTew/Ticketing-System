package com.NavyaLearning.inventoryservice.response;


import com.NavyaLearning.inventoryservice.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventInventoryResponse {
    private String event;
    private Long capacity;
    private Venue venue;
}
