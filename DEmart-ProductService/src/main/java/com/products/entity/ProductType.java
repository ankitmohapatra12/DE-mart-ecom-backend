package com.products.entity;



import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productTypeId;
	
	private String productType;
	
	private String productTypeDescription;
	
	private boolean isPublished =  true;
	
	
	
	@OneToMany(mappedBy = "productType",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Category> categories = new ArrayList<>();
	
}
