package com.NavyaLearning.orderservice.service;

import com.NavyaLearning.bookingservice.event.BookingEvent;
import com.NavyaLearning.orderservice.client.InventoryServiceClient;
import com.NavyaLearning.orderservice.entity.Order;
import com.NavyaLearning.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    //This service is a Kafka Listener

    private OrderRepository orderRepository;
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @KafkaListener(topics= "booking" , groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent){
        log.info("Received order event: {}", bookingEvent);

        //Create object for the DB
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);

        //Update Inventory Service
        inventoryServiceClient.updateInventory(order.getEventId(), order.getTicketCount());
        log.info("Inventory updated for event: {}, less tickets: {}", order.getEventId(), order.getTicketCount());
    }

    private Order createOrder(BookingEvent bookingEvent){
        return Order.builder()
                .customerId(bookingEvent.getEventId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }
}
