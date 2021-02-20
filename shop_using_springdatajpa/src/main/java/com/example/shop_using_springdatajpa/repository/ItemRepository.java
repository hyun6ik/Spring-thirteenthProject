package com.example.shop_using_springdatajpa.repository;

import com.example.shop_using_springdatajpa.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
    
}
