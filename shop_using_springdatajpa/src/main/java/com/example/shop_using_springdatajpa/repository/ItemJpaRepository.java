package com.example.shop_using_springdatajpa.repository;


import com.example.shop_using_springdatajpa.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemJpaRepository {
    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }



}
