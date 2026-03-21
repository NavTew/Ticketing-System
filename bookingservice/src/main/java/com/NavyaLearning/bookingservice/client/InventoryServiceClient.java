package com.NavyaLearning.bookingservice.client;

import com.NavyaLearning.bookingservice.response.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;
    public InventoryResponse getInventory(final Long eventId){
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl+"/event/"+eventId, InventoryResponse.class);

    }
}
