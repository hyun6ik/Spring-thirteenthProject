package com.example.shop_using_springdatajpa.controller;

import com.example.shop_using_springdatajpa.domain.Member;
import com.example.shop_using_springdatajpa.domain.Order;
import com.example.shop_using_springdatajpa.domain.item.Item;
import com.example.shop_using_springdatajpa.service.ItemService;
import com.example.shop_using_springdatajpa.service.MemberService;
import com.example.shop_using_springdatajpa.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findAllMember();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
}
