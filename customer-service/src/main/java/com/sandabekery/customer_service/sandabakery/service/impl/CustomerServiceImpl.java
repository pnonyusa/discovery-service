package com.sandabekery.customer_service.sandabakery.service.impl;

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
import com.sandabekery.customer_service.sandabakery.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    private final SandaDataToDtoMapper sandaDataToDtoMapper;

    private  final SandaDtoToJpaMapper sandaDtoToJpaMapper;


    @Override
    public SandaCustomerData addOrUpdate(SandaCustomerData bean) {
        SandaCustomerData responseBean = new SandaCustomerData();

        CustomerDto customerDto = addOrUpdate(sandaDataToDtoMapper.mapFromDataToDto(bean.getCustomerData(),CustomerDto.class));
        responseBean.setCustomerData(sandaDataToDtoMapper.mapFromDtoToData(customerDto,CustomerData.class));
        responseBean.setAddressesList(new ArrayList<>());
        bean.getAddressesList().forEach(item->handleAddress(responseBean,item));
        return responseBean;
    }

    @Override
    public void deleteAddress(Long addressUid) {
        Optional<AddressJpa> jpa = addressRepository.findById(addressUid);

        if (jpa.isPresent()){
            AddressJpa current = jpa.get();
            addressRepository.delete(current);
        }
    }

    @Override
    public void deleteCustomer(Long customerUid) {
        List<AddressJpa> addresses = addressRepository.findByCustomerUid(customerUid);
        Optional<CustomerJpa> jpa = customerRepository.findById(customerUid);

        if(jpa.isPresent()){
            CustomerJpa current = jpa.get();
            customerRepository.delete(current);
        }

        if(!addresses.isEmpty()){
            addresses.forEach(x->deleteAddress(x.getUid()));
        }
    }

    @Override
    public List<AddressDto> findByCustomerUid(Long customerUid) {

        List<AddressDto> addresses = sandaDtoToJpaMapper.mapList(addressRepository.findByCustomerUid(customerUid),AddressDto.class);
        if(addresses.isEmpty()){
            return new ArrayList<>();
        }
        return addresses;
    }

    @Override
    public SandaCustomerData findByUid(Long uid) {

        Optional<CustomerJpa> jpa = customerRepository.findByUid(uid);
        if(jpa.isPresent()){
            List<AddressJpa> addressJpa = addressRepository.findByCustomerUid(jpa.get().getUid());

            return new SandaCustomerData(sandaDataToDtoMapper.mapFromDtoToData(sandaDtoToJpaMapper.mapFromJpaToDto(jpa.get(),CustomerDto.class),CustomerData.class),
                                         sandaDataToDtoMapper.mapList(sandaDtoToJpaMapper.mapList(addressJpa,AddressDto.class),AddressData.class));
        }
        return new SandaCustomerData();
    }

    @Override
    public List<CustomerDto> findAll() {

        List<CustomerDto> dto = sandaDtoToJpaMapper.mapList(customerRepository.findAll(),CustomerDto.class);
        if(!dto.isEmpty()){
            return dto;
        }
        return new ArrayList<>();
    }


    private AddressDto addOrUpdate(AddressDto dto){

        if(dto.getUid() == null){
            dto.setUid(0L);
        }

        AddressJpa jpa = sandaDtoToJpaMapper.mapFromDtoToJpa(dto,AddressJpa.class);
        Optional <AddressJpa> dbObj = addressRepository.findById(jpa.getUid());

        if (dbObj.isPresent()){
            AddressJpa current = dbObj.get();
            BeanUtils.copyProperties(jpa,current);
            return sandaDtoToJpaMapper.mapFromJpaToDto(addressRepository.saveAndFlush(current),AddressDto.class);
        }
        else {
            return sandaDtoToJpaMapper.mapFromJpaToDto(addressRepository.saveAndFlush(jpa),AddressDto.class);
        }
    }

    private CustomerDto addOrUpdate(CustomerDto dto){
        if(dto.getUid() == null){
            dto.setUid(0L);
        }

        CustomerJpa jpa = sandaDtoToJpaMapper.mapFromDtoToJpa(dto,CustomerJpa.class);
        Optional <CustomerJpa> dbObj = customerRepository.findByUid(jpa.getUid());

        if (dbObj.isPresent()){
            CustomerJpa current = dbObj.get();
            BeanUtils.copyProperties(jpa,current);
            return sandaDtoToJpaMapper.mapFromJpaToDto(customerRepository.saveAndFlush(current),CustomerDto.class);
        }
        else {
            return sandaDtoToJpaMapper.mapFromJpaToDto(customerRepository.saveAndFlush(jpa),CustomerDto.class);
        }
    }

    private void handleAddress(SandaCustomerData response, AddressData addressData){
        addressData.setCustomerUid(response.getCustomerData().getUid());
        AddressDto addressDto = addOrUpdate(sandaDataToDtoMapper.mapFromDataToDto(addressData, AddressDto.class));
        response.getAddressesList().add(sandaDataToDtoMapper.mapFromDtoToData(addressDto, AddressData.class));
    }
}
