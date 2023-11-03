package com.products.dto;

import java.util.List;
import java.util.Set;

import com.products.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPayload {

	
	private List<Product> products;
	private long totalProductsSize;
	private long productsSize;
	private Set<String> subCategoryName;
}
