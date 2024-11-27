package com.svl.servicebase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-controller")
public class FirstController {

    @GetMapping
    public ResponseEntity<?> get(){

        return null;
    }
}
