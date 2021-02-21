package com.example.shop_using_springdatajpa.repository;

import com.example.shop_using_springdatajpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberById(Long id);

    @Query("select m from Member m where m.name =:name")
    List<Member> findByName(@Param("name") String name);
}
