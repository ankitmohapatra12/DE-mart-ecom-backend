package com.order.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {

	
	private long propertyId;
	
	private String propertyName;
	
	private String propertyValue;
	
	private boolean active = true;
	
	private Product productProperty;
}
