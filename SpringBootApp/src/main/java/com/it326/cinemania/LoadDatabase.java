package com.it326.cinemania;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Account("Daniel", "Beyer", "realEmail@gmail.com")));
            log.info("Preloading " + repository.save(new Account("AlsoRealEmail@gmail.com")));
        };
    }
}
