package com.NavyaLearning.bookingservice.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEvent {
    private Long userId;
    private Long eventId;
    private Long ticketCount;
    private BigDecimal totalPrice;
}

//A shared client between two classes is more elegant
//We just recreated the same tree and format the Producer used
