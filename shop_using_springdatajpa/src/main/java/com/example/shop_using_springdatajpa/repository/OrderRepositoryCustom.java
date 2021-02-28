package com.example.shop_using_springdatajpa.repository;

import com.example.shop_using_springdatajpa.domain.Order;
import com.example.shop_using_springdatajpa.domain.OrderSearch;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findAllByCriteria(OrderSearch orderSearch);
}
