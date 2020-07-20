package org.springguru.msscbrewery.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springguru.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

@Component
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDTO) {
        return CustomerDto.builder().id(UUID.randomUUID())
                .name("Joe Black").build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDTO) {
        log.info("Updated customer.");
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.info("Deleted customer.");
    }
}
