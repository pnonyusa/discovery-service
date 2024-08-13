package com.sandabekery.customer_service.sandabakery.repository.customer;

import com.sandabekery.customer_service.sandabakery.model.jpa.CustomerJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerJpa,Long> {
     Optional<CustomerJpa> findByUid(Long uid);
}
