package com.order.payload;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private long productId;
	
	private String productName;
	
	private String realPrice;
	
	private String discountedPrice;
	
	private double discounts;
	
	private long quantityInStock;
	
	
	
	
	private List<Property> productProperty;
	
	
	private Manufacturer manufacturer;
	
	private long brandId;
	
	private String vendor;
	
	
	private String productDescription;
	
	private String rating;
	
	
	private Brand brand;
	
	private long manufacturerId;
	
	private long sellerId;
	
	
	private Seller seller;
	
	
	private boolean active = true;
	
	
	
	private List<ProductImages> productImages = new ArrayList<>();
}
