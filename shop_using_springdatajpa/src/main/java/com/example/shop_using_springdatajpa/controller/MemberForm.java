package com.example.shop_using_springdatajpa.controller;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotEmpty;


@Data
@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;


    private String city;
    private String street;
    private String zipcode;


}
