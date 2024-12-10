package com.dust.monitoring.api.analytics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/endpoint")
public class ManagedApiController {

    @GetMapping("/one")
    public ResponseEntity<?> apiOne(){
        Map<String , String> response = new HashMap<>();
        response.put("response", "This is Api 1");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/two")
    public ResponseEntity<?> apiTwo(){
        Map<String , String> response = new HashMap<>();
        response.put("response", "This is Api 2");
        return ResponseEntity.ok(response);
    }

}
