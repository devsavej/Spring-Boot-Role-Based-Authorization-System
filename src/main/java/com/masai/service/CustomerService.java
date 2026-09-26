package com.masai.service;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer registerCustomer(Customer customer)throws CustomerException;

    public Customer getCustomerByEmail(String email)throws CustomerException;

    public List<Customer> getAllCustomerDetails()throws CustomerException;
}
