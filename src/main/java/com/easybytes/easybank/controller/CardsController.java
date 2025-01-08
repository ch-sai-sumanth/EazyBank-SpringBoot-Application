package com.easybytes.easybank.controller;


import com.easybytes.easybank.Repository.CardsRepository;
import com.easybytes.easybank.Repository.CustomerRepository;
import com.easybytes.easybank.model.Cards;
import com.easybytes.easybank.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;


    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam String email) {

        Optional<Customer> optionsalCustomer = customerRepository.findByEmail(email);
        if(optionsalCustomer.isPresent()){
            List<Cards> cards = cardsRepository.findByCustomerId(optionsalCustomer.get().getId());
            if (cards != null ) {
                return cards;
            }else {
                return null;
            }
        }else{
            return null;
        }
    }


    }
