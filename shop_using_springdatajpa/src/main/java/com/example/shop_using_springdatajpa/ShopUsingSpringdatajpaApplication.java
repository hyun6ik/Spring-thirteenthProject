package com.example.shop_using_springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopUsingSpringdatajpaApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();
		System.out.println("data = " + data);
		SpringApplication.run(ShopUsingSpringdatajpaApplication.class, args);
	}

}
