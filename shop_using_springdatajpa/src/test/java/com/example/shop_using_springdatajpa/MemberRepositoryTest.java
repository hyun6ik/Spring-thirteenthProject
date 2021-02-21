package com.example.shop_using_springdatajpa;

import com.example.shop_using_springdatajpa.domain.Member;
import com.example.shop_using_springdatajpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");
        //when
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findMemberById(savedMember.getId());

        //then
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
        assertThat(findMember.getName()).isEqualTo(savedMember.getName());
        assertThat(findMember).isEqualTo(savedMember);
    }

}