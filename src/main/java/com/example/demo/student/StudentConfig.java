package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.JANUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> { //ctrl + v
            Students mariam = new Students(
                    "Mariam",
                    "mariam@gmail.com",
                    LocalDate.of(2000, 01, 5));

            Students alex = new Students(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, 01, 5));
            repository.saveAll(
                    List.of(mariam,alex)
            );
        };
    }
}
