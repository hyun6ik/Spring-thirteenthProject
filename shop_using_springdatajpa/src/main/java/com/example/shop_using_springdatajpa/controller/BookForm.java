package com.example.shop_using_springdatajpa.controller;

import lombok.Data;

@Data
public class BookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
