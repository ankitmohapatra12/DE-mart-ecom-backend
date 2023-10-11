package com.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.entity.SubCategory;


@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
