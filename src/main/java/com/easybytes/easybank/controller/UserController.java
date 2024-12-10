package com.easybytes.easybank.controller;

import com.easybytes.easybank.Repository.CustomerRepository;
import com.easybytes.easybank.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody Customer customer) {

        try{
            String encodedPassword =passwordEncoder.encode(customer.getPwd());
            customer.setPwd(encodedPassword);
            Customer savedCustomer=customerRepository.save(customer);

            if(savedCustomer.getId()>0){
                return ResponseEntity.ok("User created successfully");
            }
            else{
                return ResponseEntity.badRequest().body("User creation failed");
            }
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
