package uk.co.zenitech.lukasj.kuberfriend.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
