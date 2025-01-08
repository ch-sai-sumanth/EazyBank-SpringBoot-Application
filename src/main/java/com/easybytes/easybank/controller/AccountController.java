package com.easybytes.easybank.controller;


import com.easybytes.easybank.Repository.AccountsRepository;
import com.easybytes.easybank.Repository.CustomerRepository;
import com.easybytes.easybank.model.Accounts;
import com.easybytes.easybank.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {

        Optional<Customer> optionsalCustomer = customerRepository.findByEmail(email);

        if(optionsalCustomer.isPresent()){
            Accounts accounts = accountsRepository.findByCustomerId(optionsalCustomer.get().getId());
            if (accounts != null) {
                return accounts;
            } else {
                return null;
            }
        }
        else{
            return null;
        }

    }

}
