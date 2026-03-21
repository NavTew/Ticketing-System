package com.NavyaLearning.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_capacity")
    private Long totalCapacity;

    @Column(name = "left_capacity")
    private Long leftCapacity;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Venue venue;

}
