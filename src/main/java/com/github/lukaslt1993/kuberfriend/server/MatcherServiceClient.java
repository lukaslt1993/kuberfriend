package com.github.lukaslt1993.kuberfriend.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MatcherServiceClient {

    private final RestTemplate client = new RestTemplate();
    private String url;

    @Autowired
    public void setUrl(@Value("${kuberfriend.service.address}") String url) {
        this.url = url;
    }

    public String getMatch(String input, String bookTitle) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        builder.queryParam("input", input);
        builder.queryParam("bookTitle", bookTitle);
        return client.getForObject(builder.build().toUri(), String.class);
    }
}
