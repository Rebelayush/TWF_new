package com.example.TWF.assessment.controller;


import com.example.TWF.assessment.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/calculate-cost")
    public ResponseEntity<Integer> calculateCost(@RequestBody Map<String, Integer> order) {
        int cost = deliveryService.calculateMinimumCost(order);
        return ResponseEntity.ok(cost);
    }
}
