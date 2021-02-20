package com.example.shop_using_springdatajpa.service;

import com.example.shop_using_springdatajpa.domain.Delivery;
import com.example.shop_using_springdatajpa.domain.Member;
import com.example.shop_using_springdatajpa.domain.Order;
import com.example.shop_using_springdatajpa.domain.OrderItem;
import com.example.shop_using_springdatajpa.domain.item.Item;
import com.example.shop_using_springdatajpa.repository.ItemRepository;
import com.example.shop_using_springdatajpa.repository.MemberRepository;
import com.example.shop_using_springdatajpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //상품 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findById(memberId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();


    }

    //상품 취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElse(null);
        order.cancel();
    }


    // 상품 조회
    public Order findOne(Long orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    //
}
