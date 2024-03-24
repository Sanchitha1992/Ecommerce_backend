package com.learnjava.api.orderservices.repository;

import com.learnjava.api.orderservices.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<Order, Long> {
}
