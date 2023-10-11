package com.demart.manufacturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;
	
	private String itemName;
	
	private long perDayProduction;
	
	private boolean active = true;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Manufacturer manufacturer_item;
}
