package com.github.lukaslt1993.kuberfriendprocessor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findFirstByTitle(String title);
}
