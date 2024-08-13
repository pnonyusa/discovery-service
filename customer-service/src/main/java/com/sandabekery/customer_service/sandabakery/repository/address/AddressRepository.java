package com.sandabekery.customer_service.sandabakery.repository.address;

import com.sandabekery.customer_service.sandabakery.model.jpa.AddressJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressJpa,Long> {
    List<AddressJpa> findByCustomerUid(Long customerUid);
}
