package uk.co.zenitech.lukasj.kuberfriend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import uk.co.zenitech.lukasj.kuberfriend.server.repository.Book;
import uk.co.zenitech.lukasj.kuberfriend.server.repository.BookRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application {

    private static final String EXT = ".txt";
    private static List<String> bookTitles = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static String[] readInput(InputStream is) throws Exception {
        return new String(is.readAllBytes()).split("\\.");
    }

    @Bean
    CommandLineRunner loadData (BookRepository repo) {
        return args -> {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:/*" + EXT);

            for (Resource r: resources) {
                String bookTitle = r.getFilename().replace(EXT, "");
                bookTitles.add(bookTitle);
                Book book = new Book(bookTitle, new ArrayList<>());
                List<String> sentences = new ArrayList<>();
                Collections.addAll(sentences, readInput(r.getInputStream()));
                book.setSentences(sentences);
                repo.save(book);
            }

            bookTitles = Collections.unmodifiableList(bookTitles);
        };
    }

    public static List<String> getBookTitles () {
        return bookTitles;
    }

}
