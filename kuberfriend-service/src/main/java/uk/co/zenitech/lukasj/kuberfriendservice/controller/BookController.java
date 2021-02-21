package uk.co.zenitech.lukasj.kuberfriendservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.zenitech.lukasj.kuberfriendservice.repository.Book;
import uk.co.zenitech.lukasj.kuberfriendservice.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
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
