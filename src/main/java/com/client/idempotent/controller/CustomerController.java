package com.client.idempotent.controller;

import com.client.idempotent.model.Customer;
import com.client.idempotent.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/created")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(this.customerService.saveCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        List <Customer> list = this.customerService.getAllCustomers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = this.customerService.findByIdCustomer(id).get();
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(this.customerService.updateCustomer(customer), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
         this.customerService.deleteCustomer(id);
        return  new ResponseEntity<>(HttpStatus.resolve(204));
    }

}
