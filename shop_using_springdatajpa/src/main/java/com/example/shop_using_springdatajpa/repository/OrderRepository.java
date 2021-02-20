package com.example.shop_using_springdatajpa.repository;

import com.example.shop_using_springdatajpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
