package com.hellokoding.jpa;

import com.hellokoding.jpa.model.IDCard;
import com.hellokoding.jpa.model.Person;
import com.hellokoding.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // save idCard along with persons
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Tom", new IDCard()));
        persons.add(new Person("Daisy", new IDCard()));
        persons.add(new Person("Alex", new IDCard()));
        personRepository.saveAll(persons);
    }
}
