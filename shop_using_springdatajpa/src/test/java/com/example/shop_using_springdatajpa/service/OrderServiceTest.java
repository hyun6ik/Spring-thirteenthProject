package com.example.shop_using_springdatajpa.service;

import com.example.shop_using_springdatajpa.domain.Address;
import com.example.shop_using_springdatajpa.domain.Member;
import com.example.shop_using_springdatajpa.domain.Order;
import com.example.shop_using_springdatajpa.domain.OrderStatus;
import com.example.shop_using_springdatajpa.domain.item.Book;
import com.example.shop_using_springdatajpa.domain.item.Item;
import com.example.shop_using_springdatajpa.exception.NotEnoughStockException;
import com.example.shop_using_springdatajpa.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Item book = createBook("JPA", 10000, 10);

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order findOrder = orderRepository.findById(orderId).orElse(null);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(findOrder.getTotalPrice()).isEqualTo(10000*orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(8);

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);
        int orderCount = 11;

        //when
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
            Assertions.fail("재고 수량 부족 예외 발생");
        });
        //then

    }

    @Test
    public void 상품취소() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);
        Order findOrder = orderService.findOne(orderId);
        //then
        assertThat(item.getStockQuantity()).isEqualTo(10);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);

    }



    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}