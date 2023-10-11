package com.demart.brand.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demart.brand.entity.Brand;

@Service
public interface BrandService {

	Brand addBrand(Brand brand) throws Exception;

	List<Brand> viewAllBrands() throws Exception;

}
