package com.evonem;

import com.evonem.customer.Customer;
import com.evonem.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {
            Customer alex = new Customer(
                     "Alexa", "alex@gmail.com", 21
            );

            Customer jamila = new Customer(
                    "Jamila", "jamila@gmail.com", 20
            );
            List<Customer> customers= List.of(alex,jamila);
            customerRepository.saveAll(customers);
        };
    }
}