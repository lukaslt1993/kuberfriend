package com.github.lukaslt1993.kuberfriend.server.service;

import com.github.lukaslt1993.kuberfriend.server.repository.Book;
import com.github.lukaslt1993.kuberfriend.server.repository.BookRepository;
import com.github.lukaslt1993.kuberfriend.server.repository.TitleProjection;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    public static final String INPUT_FILE_EXTENSION = ".txt";

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<String> getBookTitles() {
        return bookRepository.findTitlesBy().stream()
                .map(TitleProjection::getTitle)
                .collect(Collectors.toList());
    }

    public void loadBooksIntoDB() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/*" + INPUT_FILE_EXTENSION);

        for (Resource r: resources) {
            String bookTitle = r.getFilename().replace(INPUT_FILE_EXTENSION, "");
            if (bookRepository.findBooksByTitle(bookTitle).isEmpty()) {
                Book book = new Book(bookTitle, new ArrayList<>());
                List<String> sentences = new ArrayList<>();
                Collections.addAll(sentences, readSentences(r.getInputStream()));
                book.setSentences(sentences);
                bookRepository.save(book);
            }
        }
    }

    private String[] readSentences(InputStream is) throws Exception {
        return new String(is.readAllBytes()).split("[.?!]");
    }

}
