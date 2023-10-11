package com.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.entity.CategoryImages;

@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImages, Long> {

}
