package com.products.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.products.dto.Brand;
import com.products.dto.Seller;

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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	private String productName;
	
	private String realPrice;
	
	private String discountedPrice;
	
	private double discounts;
	
	private long quantityInStock;
	
	private String manufacturer;
	
	private long brandId;
	
	private String vendor;
	
	private String productDescription;
	
	private String rating;
	
	@Transient
	private Brand brand;
	
	
	private long sellerId;
	
	@Transient
	private Seller seller;
	
	
	private boolean active = true;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private SubCategory subCategoryProducts; 
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ProductImages> productImages = new ArrayList<>();
	
	
	
	
}
