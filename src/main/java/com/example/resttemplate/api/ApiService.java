package com.example.resttemplate.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    RestTemplate restTemplate;

    public String getCatFacts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                "https://cat-fact.herokuapp.com/facts/",
                HttpMethod.GET, entity, String.class
        ).getBody();
    }
}