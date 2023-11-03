package com.products.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	
	private long itemId;
	
	private String itemName;
	
	private long perDayProduction;
	
	private boolean active = true;
	
	private Manufacturer manufacturer_item;
}
