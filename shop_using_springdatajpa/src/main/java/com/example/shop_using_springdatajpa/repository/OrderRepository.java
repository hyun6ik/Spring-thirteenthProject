package com.example.shop_using_springdatajpa.repository;

import com.example.shop_using_springdatajpa.domain.Order;
import com.example.shop_using_springdatajpa.domain.OrderSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>, OrderRepositoryCustom {

    Order findOrderById(Long id);
}
