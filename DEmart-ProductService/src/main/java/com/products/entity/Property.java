package com.products.entity;

																																																																																																																																																																																																																																																																																			
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long propertyId;
	
	private String propertyName;
	
	private String propertyValue;
	
	private boolean active = true;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Product productProperty;
}
