package com.example.shop_using_springdatajpa.controller;

import lombok.Data;

@Data
public class BookForm {

    private String name;
    private Long id;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

}
