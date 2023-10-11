package com.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.entity.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
