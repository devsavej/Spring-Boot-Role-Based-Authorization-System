package com.masai.service;

import com.masai.exceptions.CustomerException;

import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByEmail(String email) throws CustomerException {
        return customerRepository.findByEmail(email).orElseThrow(()->new CustomerException("Customer Not Found With Email:"+email));
    }

    @Override
    public List<Customer> getAllCustomerDetails() throws CustomerException {
        List<Customer> list=customerRepository.findAll();
        if (list.isEmpty()){
            throw new CustomerException("Customer Not Found");
        }else {
            return list;
        }
    }
}
