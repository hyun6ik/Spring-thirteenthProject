package com.example.shop_using_springdatajpa.repository;

import com.example.shop_using_springdatajpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberById(Long id);

}
