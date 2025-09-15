package edu.demo.proxy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentAdapterController {
    @PostMapping("/process")
    public String process(@RequestParam double amount){ if(amount > 100) return "EXTERNAL_DECLINED"; return "EXTERNAL_APPROVED"; }
}
