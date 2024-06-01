package com.client.idempotent.service;

import com.client.idempotent.model.Customer;
import com.client.idempotent.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Optional<Customer> findByIdCustomer(Long id) {
      return  this.customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        if(findByIdCustomer(customer.getId()).isEmpty()){
             throw new RuntimeException();
        }
        return this.customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if(this.findByIdCustomer(id).isEmpty()){
            throw new RuntimeException();
        }
        this.customerRepository.deleteById(id);
    }

}
