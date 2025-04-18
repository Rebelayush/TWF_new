package com.example.TWF.assessment.service;

import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class DeliveryService {

    private final Map<String, Integer> costPerProduct = Map.of(
            "A", 26, "B", 26, "C", 26,
            "D", 90, "E", 90, "F", 90,
            "G", 12, "H", 12, "I", 12
    );

    private final Map<String, String> productToCenter = Map.ofEntries(
            Map.entry("A", "C1"), Map.entry("B", "C1"), Map.entry("C", "C1"),
            Map.entry("D", "C2"), Map.entry("E", "C2"), Map.entry("F", "C2"),
            Map.entry("G", "C3"), Map.entry("H", "C3"), Map.entry("I", "C3")
    );

    public int calculateMinimumCost(Map<String, Integer> order) {
        int totalCost = 0;
        Map<String, Integer> centerProductCount = new HashMap<>();
        int totalProducts = 0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();

            Integer productCost = costPerProduct.get(product);
            if (productCost == null) {
                throw new IllegalArgumentException("Invalid product: " + product);
            }

            totalCost += productCost * quantity;
            totalProducts += quantity;

            String center = productToCenter.get(product);
            if (center != null) {
                centerProductCount.put(center,
                        centerProductCount.getOrDefault(center, 0) + quantity);
            }
        }

        // Apply penalty if products are nearly evenly split between multiple centers
        if (centerProductCount.size() > 1) {
            int max = Collections.max(centerProductCount.values());
            int min = Collections.min(centerProductCount.values());

            if ((max - min) <= 1) {  // even or nearly even split
                totalCost += 4;
            }
        }

        return totalCost;
    }



}
