package com.example.shop_using_springdatajpa.service;

import com.example.shop_using_springdatajpa.domain.Member;
import com.example.shop_using_springdatajpa.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");
        memberService.join(member);
        //when
        Member findMember = memberService.findMember(member.getId());
        //then
        assertThat(member).isEqualTo(findMember);
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test()
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Yoon1");

        Member member2 = new Member();
        member2.setName("Yoon1");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class,() ->{
            memberService.join(member2); //예외발생
        });


    }

}