package edu.demo.event.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PublisherController {
    private final ApplicationEventPublisher publisher;
    public PublisherController(ApplicationEventPublisher publisher){ this.publisher = publisher; }
    @PostMapping("/publish")
    public String publish(@RequestBody Map<String,Object> payload){ publisher.publishEvent(new edu.demo.event.controller.SimplePayloadEvent(this, payload)); return "PUBLISHED"; }
}
