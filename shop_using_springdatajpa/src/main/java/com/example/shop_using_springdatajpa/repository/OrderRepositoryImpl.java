package com.example.shop_using_springdatajpa.repository;

import com.example.shop_using_springdatajpa.domain.Member;
import com.example.shop_using_springdatajpa.domain.Order;
import com.example.shop_using_springdatajpa.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);
            Root<Order> o = cq.from(Order.class);
            Join<Order, Member> m = o.join("member", JoinType.INNER);

            List<Predicate> criteria = new ArrayList<>();
            if (orderSearch.getOrderStatus() != null) {
                Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
                criteria.add(status);
            }
//회원 이름 검색
            if (StringUtils.hasText(orderSearch.getMemberName())) {         Predicate name =
                    cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
                criteria.add(name);
            }
            cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
            TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
            return query.getResultList();
        }
    }

