package com.github.lukaslt1993.kuberfriendprocessor.controller;

import com.github.lukaslt1993.kuberfriendprocessor.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String getMatch(String input, String bookTitle) {
        return service.getMatch(input, bookTitle);
    }

}
