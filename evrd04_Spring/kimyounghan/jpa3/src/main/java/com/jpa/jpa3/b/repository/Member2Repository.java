package com.jpa.jpa3.b.repository;


import com.jpa.jpa3.b.domain.Member2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Member2Repository {

    @PersistenceContext
    private EntityManager em;

    public List<Member2> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member2.class)
                .setParameter("name", name)
                .getResultList();
    }


}
