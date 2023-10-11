package com.products.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;
	
	private String categoryName;
	
	
	

	private String discounts;
	
	private String categoryDescription;
	
	private boolean active;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ProductType productType;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<CategoryImages> categoryImages = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "category_sub",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private List<SubCategory> subCategories = new ArrayList<>();
	
	
	
	
	
	
	
	
	
	
	

}
