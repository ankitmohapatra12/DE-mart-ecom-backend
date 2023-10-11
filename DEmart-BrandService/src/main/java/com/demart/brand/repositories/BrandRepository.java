package com.demart.brand.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demart.brand.entity.Brand;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
