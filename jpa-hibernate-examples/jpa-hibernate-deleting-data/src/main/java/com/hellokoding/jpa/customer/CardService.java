package com.hellokoding.jpa.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public void deleteByCustomerId(Integer customerId) {
        cardRepository.deleteByCustomerId(customerId);
    }
}
