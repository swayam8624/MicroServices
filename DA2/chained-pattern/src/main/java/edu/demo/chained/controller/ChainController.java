package edu.demo.chained.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChainController {
    private final RestTemplate rest = new RestTemplate();
    @GetMapping("/start")
    public String startChain(){ String stepA = rest.getForObject("https://httpbin.org/get", String.class); String stepB = rest.getForObject("https://jsonplaceholder.typicode.com/todos/1", String.class); return "STEP A OK\nSTEP B OK"; }
}
