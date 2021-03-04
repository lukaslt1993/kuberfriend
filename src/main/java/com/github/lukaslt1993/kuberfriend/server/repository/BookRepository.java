package com.github.lukaslt1993.kuberfriend.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
