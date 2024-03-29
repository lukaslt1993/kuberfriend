package com.github.lukaslt1993.kuberfriendprocessor.service;

import com.github.lukaslt1993.kuberfriendprocessor.repository.Book;
import com.github.lukaslt1993.kuberfriendprocessor.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public String getMatch(String input, String bookTitle) {
        Book book = repo.findFirstByTitle(bookTitle);
        List<String> matches = new ArrayList<>();
        int maxWordsMatched = 0;
        String[] inputWords = input.split(" ");

        for (String sentence : book.getSentences()) {
            int wordsMatched = wordsMatched(inputWords, sentence.split(" "));

            if (wordsMatched == maxWordsMatched) {
                matches.add(sentence);
            } else if (wordsMatched > maxWordsMatched) {
                matches.clear();
                matches.add(sentence);
                maxWordsMatched = wordsMatched;
            }

        }

        Collections.shuffle(matches);
        return matches.stream().findFirst().orElse("No matches found");
    }

    private int wordsMatched(String[] arr1, String[] arr2) {
        int wordsMatched = 0;

        for (String s1 : arr1) {
            for (String s2 : arr2) {
                if (s1.equalsIgnoreCase(s2)) {
                    wordsMatched++;
                    break;
                }
            }
        }

        return wordsMatched;
    }

}
