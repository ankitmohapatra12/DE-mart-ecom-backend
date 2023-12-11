package com.review.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	
	private long itemId;
	
	private String itemName;
	
	private long perDayProduction;
	
	private boolean active = true;
	
	private Manufacturer manufacturer_item;
}
