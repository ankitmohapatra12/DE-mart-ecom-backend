package com.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.products.entity.Product;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT * FROM product WHERE sub_category_products_sub_category_id = :subCategoryId", nativeQuery = true)
	List<Product> getProductsBasedOnSubCategoryId(long subCategoryId);
	
	
	
	
     
	@Query(value = "(:jpqlQuery)",
	        nativeQuery = true)
	List<Product> getProductsBasedOnCategory(@Param("jpqlQuery") String jpqlQuery);





	Product findByProductId(int productId);
}





