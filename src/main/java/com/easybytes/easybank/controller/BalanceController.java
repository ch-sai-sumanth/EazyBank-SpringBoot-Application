package com.easybytes.easybank.controller;


import com.easybytes.easybank.Repository.AccountTransactionsRepository;
import com.easybytes.easybank.Repository.CustomerRepository;
import com.easybytes.easybank.model.AccountTransactions;
import com.easybytes.easybank.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;


    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {

        Optional<Customer> optionsalCustomer = customerRepository.findByEmail(email);
        if(optionsalCustomer.isPresent()){
            List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                    findByCustomerIdOrderByTransactionDtDesc(optionsalCustomer.get().getId());
            if (accountTransactions != null) {
                return accountTransactions;
            } else {
                return null;
            }
        }else{
            return null;
        }
    }



}