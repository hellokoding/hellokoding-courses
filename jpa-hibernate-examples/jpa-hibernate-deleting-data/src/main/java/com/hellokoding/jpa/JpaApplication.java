package com.hellokoding.jpa;

import com.hellokoding.jpa.customer.Card;
import com.hellokoding.jpa.customer.CardService;
import com.hellokoding.jpa.customer.Customer;
import com.hellokoding.jpa.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.util.List;

import static com.hellokoding.jpa.customer.Card.MASTER;
import static com.hellokoding.jpa.customer.Card.VISA;

@Slf4j
@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(CustomerService customerService, CardService cardService) {
        return r -> {
            log.info("Soft deleting manually...");
            Customer custA = customerService.save(new Customer("A",
            new Card(123, VISA),
            new Card(213, MASTER)));
            customerService.softDelete(custA.getId());

            log.info("Deleting data with CascadeType...");
            Customer custB = customerService.save(new Customer("B",
                new Card(124, VISA),
                new Card(214, MASTER)));
            customerService.delete(custB);

            Customer custC = customerService.save(new Customer("C",
                new Card(125, VISA),
                new Card(215, MASTER)));
            customerService.deleteById(custC.getId());

            log.info("Deleting data with orphanRemoval...");
            Customer custD = customerService.save(new Customer("D",
                new Card(126, VISA),
                new Card(216, MASTER)));
            custD.getCards().removeIf(c -> VISA.equals(c.getType()));
            customerService.save(custD);

            log.info("Deleting data with JPQL");
            Customer custE = customerService.save(new Customer("E",
                new Card(127, VISA),
                new Card(217, MASTER)));
            cardService.deleteByCustomerId(custE.getId());
            customerService.deleteByIdWithJPQL(custE.getId());

            customerService.save(new Customer("F",
                new Card(128, VISA),
                new Card(218, MASTER)));

            log.info("Find top 10 customers");
            List<Customer> customers = customerService.findTop10();
            for (Customer customer : customers) {
                log.info(customer.toString());

                for (Card card : customer.getCards()) {
                    log.info(card.toString());
                }
            }
        };
    }
}
