package com.sandabekery.customer_service.service;


import com.sandabekery.customer_service.api.client.model.AddressData;
import com.sandabekery.customer_service.api.client.model.CustomerData;
import com.sandabekery.customer_service.api.client.model.SandaCustomerData;
import com.sandabekery.customer_service.sandabakery.model.dto.AddressDto;
import com.sandabekery.customer_service.sandabakery.model.dto.CustomerDto;
import com.sandabekery.customer_service.sandabakery.model.jpa.AddressJpa;
import com.sandabekery.customer_service.sandabakery.model.jpa.CustomerJpa;
import com.sandabekery.customer_service.sandabakery.model.mapper.SandaDataToDtoMapper;
import com.sandabekery.customer_service.sandabakery.model.mapper.SandaDtoToJpaMapper;
import com.sandabekery.customer_service.sandabakery.repository.address.AddressRepository;
import com.sandabekery.customer_service.sandabakery.repository.customer.CustomerRepository;
import com.sandabekery.customer_service.sandabakery.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private SandaDataToDtoMapper sandaDataToDtoMapper;

    @Mock
    private SandaDtoToJpaMapper sandaDtoToJpaMapper;


    @InjectMocks
    private CustomerServiceImpl customerService;


    private CustomerDto customerDto;
    private CustomerJpa customerJpa;
    private AddressDto addressDto;
    private AddressJpa addressJpa;
    private CustomerData customerData;
    private AddressData addressData;


    @BeforeEach
    void setup(){

       addressJpa =  new AddressJpa(1L,"Dyakophu","409","7750",1L);
       addressDto =  new AddressDto(1L,"Dyakophu","409","7750",1L);
       addressData =  new AddressData(1L,"Dyakophu","409","7750",1L);

       customerDto = new CustomerDto(1L,"Phaphamani","Nonyusa","pnonyusa@gmail.com");
       customerJpa = new CustomerJpa(1L,"Phaphamani","Nonyusa","pnonyusa@gmail.com");
       customerData = new CustomerData(1L,"Phaphamani","Nonyusa","pnonyusa@gmail.com");
    }



    @Test
   void test_AddOrUpdate(){

        when(sandaDtoToJpaMapper.mapFromDtoToJpa(customerDto,CustomerJpa.class))
                .thenReturn(customerJpa);

        when(customerRepository.findByUid(customerDto.getUid())).
                thenReturn(Optional.of(customerJpa));

        when(sandaDtoToJpaMapper.mapFromJpaToDto(customerRepository.saveAndFlush(customerJpa),CustomerDto.class))
                .thenReturn(customerDto);

        when(sandaDtoToJpaMapper.mapFromDtoToJpa(addressDto,AddressJpa.class))
                .thenReturn(addressJpa);

        when(addressRepository.findById(addressDto.getUid())).
                thenReturn(Optional.of(addressJpa));

        when(sandaDtoToJpaMapper.mapFromJpaToDto(addressRepository.saveAndFlush(addressJpa),AddressDto.class))
                .thenReturn(addressDto);

        when(sandaDataToDtoMapper.mapFromDataToDto(addressData,AddressDto.class))
                .thenReturn(addressDto);

        when(sandaDataToDtoMapper.mapFromDtoToData(addressDto,AddressData.class))
                .thenReturn(addressData);

        when(sandaDataToDtoMapper.mapFromDataToDto(customerData,CustomerDto.class))
                .thenReturn(customerDto);

        when(sandaDataToDtoMapper.mapFromDtoToData(customerDto,CustomerData.class))
                .thenReturn(customerData);

        SandaCustomerData responseBean = customerService.addOrUpdate(new SandaCustomerData(customerData,
                Collections.singletonList(addressData)));

        assertNotNull(responseBean);
    }


    @Test
    void test_FindAll(){

        when(sandaDtoToJpaMapper.mapList(customerRepository.findAll(),CustomerDto.class)).
                thenReturn(Collections.singletonList(customerDto));

        List<CustomerDto> response = customerService.findAll();

        assertEquals(response.size(),1);
    }

    @Test
    void test_FindByUid(){

        when(customerRepository.findByUid(customerDto.getUid()))
                .thenReturn(Optional.of(customerJpa));

         when(addressRepository.findByCustomerUid(customerJpa.getUid()))
                 .thenReturn(Collections.singletonList(addressJpa));

         when(sandaDataToDtoMapper.mapFromDtoToData(customerDto,CustomerData.class))
                 .thenReturn(customerData);

        when(sandaDataToDtoMapper.mapFromDtoToData(customerDto,CustomerData.class))
                .thenReturn(customerData);

        when(sandaDtoToJpaMapper.mapFromJpaToDto(customerJpa,CustomerDto.class))
                .thenReturn(customerDto);

        when(sandaDataToDtoMapper.mapList(Collections.singletonList(addressDto),AddressData.class))
                .thenReturn(Collections.singletonList(addressData));

        when(sandaDtoToJpaMapper.mapList(Collections.singletonList(addressJpa),AddressDto.class))
                .thenReturn(Collections.singletonList(addressDto));

        SandaCustomerData response = customerService.findByUid(customerDto.getUid());

        assertNotNull(response);

    }

    @Test
    void test_FindByCustomerUid(){

        when(sandaDtoToJpaMapper.mapList(addressRepository.findByCustomerUid(1L),AddressDto.class))
                .thenReturn(Collections.singletonList(addressDto));

        List<AddressDto> response = customerService.findByCustomerUid(1L);

        assertEquals(response.size(),1);
    }

}
