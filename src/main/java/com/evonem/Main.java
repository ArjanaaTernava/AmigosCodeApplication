package com.evonem;

import com.evonem.customer.Customer;
import com.evonem.customer.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {
            Faker faker = new Faker();
            Name name = faker.name();
            String firstName = name.firstName().toLowerCase();
            String lastName = name.lastName().toLowerCase();
            Customer customer = new Customer(
                    firstName + " " + lastName, firstName + "." + lastName + "@gmail.com",new Random().nextInt(16,99)
            );
            customerRepository.save(customer);
        };
    }
}