package com.NavyaLearning.orderservice.repository;

import com.NavyaLearning.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
