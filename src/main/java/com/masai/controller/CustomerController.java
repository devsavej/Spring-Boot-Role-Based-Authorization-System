package com.masai.controller;

import com.masai.model.Customer;
import com.masai.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String testHandler(){
        return "Welcome To Spring Security";
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> registerCustomerHandler(@RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_"+customer.getRole().toUpperCase());
        customerService.registerCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);

    }

    @GetMapping("customers/{email}")
    public ResponseEntity<Customer> getCustomerByEmailHandler(@PathVariable String email){

        Customer customer=customerService.getCustomerByEmail(email);

        return new ResponseEntity<>(customer,HttpStatus.FOUND);

    }

    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getAllCustomerDetailsHandler(){
        List<Customer> customers=customerService.getAllCustomerDetails();

        return new ResponseEntity<>(customers,HttpStatus.OK);

    }

    @GetMapping("/signIn")
    public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
        System.out.println(auth);

        Customer customer= customerService.getCustomerByEmail(auth.getName());

        return new ResponseEntity<>(customer.getName()+" Logged Successfully",HttpStatus.OK);

    }

    @PostMapping("/contact")
    public String postDemo1(){
        return "Not Harmful POST Request.";
    }
    @PutMapping("/notice")
    public String putDemo2(){
        return "Not Harmful PUT Request..";

    }
    @PostMapping("writeUs")
    public String postDemo3(){
        return "it is Harmful Post Request...";
    }

    @PutMapping("/readUs")
    public String purDemo4(){
        return "it is harmful PUT Request";
    }


}
