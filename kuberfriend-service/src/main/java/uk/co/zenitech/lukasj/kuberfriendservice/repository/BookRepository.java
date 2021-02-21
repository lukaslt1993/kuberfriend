package uk.co.zenitech.lukasj.kuberfriendservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

    public Book findFirstByTitle(String title);
}
