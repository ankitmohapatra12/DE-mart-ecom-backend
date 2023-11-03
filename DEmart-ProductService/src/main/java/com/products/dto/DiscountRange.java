package com.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRange {
    private int min_discount;
    private int max_discount;

    // Getters and setters
}
