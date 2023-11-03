package com.products.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filters {
	    private List<String> genders;
	    private List<String> subCategories;
	    private List<String> brands;
	    private List<String> colors;
	    private int min_price;
	    private int max_price;
	    private int min_discount;
	    private int max_discount;
	    private int rating;

	    // Getters and setters
}

    
   
	

