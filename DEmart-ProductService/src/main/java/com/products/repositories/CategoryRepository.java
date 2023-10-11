package com.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
