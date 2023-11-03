package com.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.products.dto.Brand;
import com.products.dto.Filters;
import com.products.entity.Category;
import com.products.entity.Product;
import com.products.entity.ProductType;
import com.products.entity.SubCategory;
import com.products.exception.ProductTypeFailedException;

@Service
public interface ProductTypeService {
	
	public ProductType addProductType(ProductType productType)  throws ProductTypeFailedException ;

	public List<ProductType> viewProductType() throws Exception;

	public Category addCategory(Category category);

	public List<Category> viewCategories()  throws Exception;

	public SubCategory addSubCategory(SubCategory subCategory);

	public List<SubCategory> viewSubCategories() throws Exception;

	public Product addProduct(Product product);

	public List<Product> viewProducts() throws Exception;
	


	public List<Brand> getAllBrands();

	public List<Product> viewSellerProducts(long sellerId) throws Exception;

	public SubCategory viewSubCategory(long subCategoryId);

	public List<Product> getProductsBasedOnSubCategory(long subCategoryId, PageRequest pageRequest,Filters filterdData) throws Exception;

	public List<Product> viewProductsBySubCategory(long subCategoryId);

	public Product viewProduct(int productId) throws Exception;
}
