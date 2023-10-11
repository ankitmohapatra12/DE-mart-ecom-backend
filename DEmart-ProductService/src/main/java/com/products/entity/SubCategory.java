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
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subCategoryId;
	
	private String subCategoryName;
	
	
	

	private String discounts;
	
	private String subCategoryDescription;
	
	private boolean active;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category_sub;
	
	@OneToMany(mappedBy = "subCategory",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<SubCategoryImages> subCategoryImages = new ArrayList<>();
	
	

	
	@OneToMany(mappedBy = "subCategoryProducts",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Product> subCategoryProducts = new ArrayList<>();
}
