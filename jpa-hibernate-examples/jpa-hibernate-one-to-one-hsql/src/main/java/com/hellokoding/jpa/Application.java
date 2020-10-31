package com.hellokoding.jpa;

import com.hellokoding.jpa.model.IDCard;
import com.hellokoding.jpa.model.Person;
import com.hellokoding.jpa.repository.IDCardRepository;
import com.hellokoding.jpa.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final IDCardRepository idCardRepository;
    private final PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) {
        Person p1 = new Person("Tom");
        Person p2 = new Person("Daisy");
        Person p3 = new Person("Alex");
        personRepository.saveAll(Arrays.asList(p1, p2, p3));

        idCardRepository.save(new IDCard(p1));
        idCardRepository.save(new IDCard(p2));
        idCardRepository.save(new IDCard(p3));
    }
}
