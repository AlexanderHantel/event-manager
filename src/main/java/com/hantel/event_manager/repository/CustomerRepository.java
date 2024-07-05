package com.hantel.event_manager.repository;

import com.hantel.event_manager.entity.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerRepository {
    private final EntityManager entityManager;

    @Autowired
    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Customer customer) {
        entityManager.persist(customer);
    }
}
