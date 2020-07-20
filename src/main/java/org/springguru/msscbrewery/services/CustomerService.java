package org.springguru.msscbrewery.services;

import org.springguru.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDTO);

    void updateCustomer(UUID customerId, CustomerDto customerDTO);

    void deleteCustomer(UUID customerId);
}
