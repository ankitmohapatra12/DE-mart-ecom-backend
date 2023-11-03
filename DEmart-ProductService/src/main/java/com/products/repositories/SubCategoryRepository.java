package com.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.products.entity.SubCategory;


@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	SubCategory findBySubCategoryId(long subCategoryId);

	
	
	@Query(value="SELECT s.sub_category_name FROM product p JOIN sub_category s ON s.sub_category_id = p.sub_category_products_sub_category_id WHERE s.sub_category_id = :productId",nativeQuery = true)
    String findSubCategoryByProductSubCategoryId(@Param("productId") Long productId);

}
