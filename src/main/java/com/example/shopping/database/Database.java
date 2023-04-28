package com.example.shopping.database;

import com.example.shopping.repositories.ProductRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product productA = new Product("iphone2", 23.04, "abc.xyz", 2021);
//                Product productB = new Product( "samsung", 23.04, "abc.xyz", 2021);
//                logger.info("Insert data: product A - " + repository.save(productA));
//                logger.info("Insert data: product B - " + repository.save(productB));
            }
        };
    }
}
