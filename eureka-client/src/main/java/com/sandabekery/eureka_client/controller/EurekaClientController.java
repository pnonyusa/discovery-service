package com.sandabekery.eureka_client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientController {

    @GetMapping
    public String greeting(){
        return "Hi EurekaClientController";
    }
}
