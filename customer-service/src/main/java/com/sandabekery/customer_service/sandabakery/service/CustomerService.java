package com.sandabekery.customer_service.sandabakery.service;

import com.sandabekery.customer_service.api.client.model.CustomerData;
import com.sandabekery.customer_service.api.client.model.SandaCustomerData;
import com.sandabekery.customer_service.sandabakery.model.dto.AddressDto;
import com.sandabekery.customer_service.sandabakery.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    SandaCustomerData addOrUpdate(SandaCustomerData customerData);
    void deleteAddress(Long addressUid);
    void deleteCustomer(Long customerUid);
    List<AddressDto> findByCustomerUid(Long customerUid);
    SandaCustomerData findByUid(Long uid);
    List<CustomerDto> findAll();
}
