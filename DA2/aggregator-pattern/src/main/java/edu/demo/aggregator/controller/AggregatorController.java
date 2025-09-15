package edu.demo.aggregator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AggregatorController {
    private final RestTemplate rest = new RestTemplate();

    @GetMapping("/aggregate")
    public Map<String,Object> aggregate(){
        Map<String,Object> result = new HashMap<>();
        Object user = rest.getForObject("https://jsonplaceholder.typicode.com/users/1", Object.class);
        Object posts = rest.getForObject("https://jsonplaceholder.typicode.com/posts?userId=1", Object.class);
        result.put("user", user);
        result.put("posts", posts);
        return result;
    }
}
