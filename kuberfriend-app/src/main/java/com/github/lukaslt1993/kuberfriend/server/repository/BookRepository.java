package com.github.lukaslt1993.kuberfriend.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findBooksByTitle(String title);

    List<TitleProjection> findTitlesBy();

}
