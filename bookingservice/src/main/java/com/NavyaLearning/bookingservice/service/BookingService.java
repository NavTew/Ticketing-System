package com.NavyaLearning.bookingservice.service;

import com.NavyaLearning.bookingservice.client.InventoryServiceClient;
import com.NavyaLearning.bookingservice.entity.Customer;
import com.NavyaLearning.bookingservice.repository.CustomerRepository;
import com.NavyaLearning.bookingservice.request.BookingRequest;
import com.NavyaLearning.bookingservice.response.BookingResponse;
import com.NavyaLearning.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    @Autowired
    public BookingService(final CustomerRepository customerRepository,  final InventoryServiceClient inventoryServiceClient) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public BookingResponse createBooking(final BookingRequest request) {
        //check if user exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if(customer == null){
            throw new RuntimeException("Customer not found");
        }

        //check if there is enough inventory
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        System.out.println("Inventory Service Response" + inventoryResponse);;
        //get event information to also get venue info, price and everything etc
        if(inventoryResponse.getCapacity() < request.getTicketCount()){
            throw new RuntimeException("Capacity less than ticket count");
        }
        //create booking
        //send booking to Order Service via Kafka queue

        return BookingResponse.builder().build();
    }
}
