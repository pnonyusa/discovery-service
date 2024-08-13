package com.sandabekery.customer_service.sandabakery.controller;


import com.sandabekery.customer_service.api.client.command.SandaCustomerCommand;
import com.sandabekery.customer_service.api.client.model.AddressData;
import com.sandabekery.customer_service.api.client.model.CustomerData;
import com.sandabekery.customer_service.api.client.model.SandaCustomerData;
import com.sandabekery.customer_service.api.client.response.AddressResponse;
import com.sandabekery.customer_service.api.client.response.CustomerResponse;
import com.sandabekery.customer_service.api.client.response.SandaCustomerResponse;
import com.sandabekery.customer_service.sandabakery.model.dto.AddressDto;
import com.sandabekery.customer_service.sandabakery.model.mapper.SandaDataToDtoMapper;
import com.sandabekery.customer_service.sandabakery.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/sandabakery")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final SandaDataToDtoMapper sandaDataToDtoMapper;

    @PostMapping("/v1.0/customer")
    public ResponseEntity<SandaCustomerResponse> addOrUpdate(@RequestBody @Valid SandaCustomerCommand command){
        SandaCustomerData data = customerService.addOrUpdate(command.getData());
        return ok(new SandaCustomerResponse(data));
    }

    @DeleteMapping("/v1.0/customer/{uid}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("uid")Long uid){
           customerService.deleteCustomer(uid);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/v1.0/address/{addressUid}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressUid")Long addressUid){
        customerService.deleteAddress(addressUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/v1.0/address/{customerUid}")
    private ResponseEntity<AddressResponse> findAddressByCustomerUid(@PathVariable("customerUid")Long customerUid){
        List<AddressDto> dto = customerService.findByCustomerUid(customerUid);
        return ok(new AddressResponse(sandaDataToDtoMapper.mapList(dto, AddressData.class)));
    }

    @GetMapping("/v1.0/customer/{uid}")
    public ResponseEntity<SandaCustomerResponse> findByUid(@PathVariable("uid")Long uid){
        return ok(new SandaCustomerResponse(customerService.findByUid(uid)));
    }

    @GetMapping("/v1.0/customers")
    public ResponseEntity<CustomerResponse> findAll(){
        List<CustomerData> data = sandaDataToDtoMapper.mapList(customerService.findAll(),CustomerData.class);
        return ok(new CustomerResponse(data));
    }

}
