package uk.co.zenitech.lukasj.kuberfriend.server.repository;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Book {

    @Id
    private String id;

    private String title;
    private List<String> sentences;

    public Book(String title, List<String> sentences) {
        this.title = title;
        this.sentences = sentences;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public String getId() {
        return id;
    }
}
