package com.example.shop_using_springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberById(Long id);

}
