package com.example.shop_using_springdatajpa.service;

import com.example.shop_using_springdatajpa.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class UpdateItemTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateItemTest() throws Exception {
        //given
        Book book = em.find(Book.class, 1L);
        //when
        book.setName("asdf");
        //then


    }
}
