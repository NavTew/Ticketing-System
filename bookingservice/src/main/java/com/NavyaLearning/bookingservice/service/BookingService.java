package com.NavyaLearning.bookingservice.service;

import com.NavyaLearning.bookingservice.client.InventoryServiceClient;
import com.NavyaLearning.bookingservice.entity.Customer;
import com.NavyaLearning.bookingservice.event.BookingEvent;
import com.NavyaLearning.bookingservice.repository.CustomerRepository;
import com.NavyaLearning.bookingservice.request.BookingRequest;
import com.NavyaLearning.bookingservice.response.BookingResponse;
import com.NavyaLearning.bookingservice.response.InventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;
    @Autowired
    public BookingService(final CustomerRepository customerRepository,  final InventoryServiceClient inventoryServiceClient, final KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookingResponse createBooking(final BookingRequest request) {
        //check if user exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if(customer == null){
            throw new RuntimeException("Customer not found");
        }

        //check if there is enough inventory
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        log.info("Inventory Service Response" + inventoryResponse);;
        //get event information to also get venue info, price and everything etc
        if(inventoryResponse.getCapacity() < request.getTicketCount()){
            throw new RuntimeException("Capacity less than ticket count");
        }
        //create booking
        final BookingEvent bookingEvent = createBookingEvent(request, customer, inventoryResponse);
        //send booking to Order Service via Kafka queue

        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking sent to Kafka: {}", bookingEvent);

        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    private BookingEvent createBookingEvent(final BookingRequest request, final Customer customer, final InventoryResponse inventoryResponse) {
        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(request.getEventId())
                .ticketCount(request.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();
    }
}
