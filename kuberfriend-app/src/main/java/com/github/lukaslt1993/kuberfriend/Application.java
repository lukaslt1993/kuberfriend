package com.github.lukaslt1993.kuberfriend;

import com.github.lukaslt1993.kuberfriend.server.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner loadData (BookService bookService) {
        return args -> bookService.loadBooksIntoDB();
    }

}
