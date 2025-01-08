package com.easybytes.easybank.controller;


import com.easybytes.easybank.Repository.CustomerRepository;
import com.easybytes.easybank.Repository.LoansRepository;
import com.easybytes.easybank.model.Customer;
import com.easybytes.easybank.model.Loans;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansRepository loanRepository;
    private final CustomerRepository customerRepository;


    @GetMapping("/myLoans")
    @PreAuthorize("hasRole('ADMIN')")
//       @PostAuthorize("hasRole('ADMIN')")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        Optional<Customer> optionsalCustomer = customerRepository.findByEmail(email);
        if(optionsalCustomer.isPresent()){
            List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(optionsalCustomer.get().getId());
            if (loans != null) {
                return loans;
            } else {
                return null;
            }
        }else{
            return null;
        }
    }
}