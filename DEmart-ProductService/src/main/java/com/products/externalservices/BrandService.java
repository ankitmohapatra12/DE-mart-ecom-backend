package com.products.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.products.dto.Brand;


@FeignClient(name="DEmart-BrandService")
public interface BrandService {

	
	@GetMapping("/brands/all/view")
	List<Brand> getBrands();
	
	@GetMapping("/brands/{id}")
	Brand getBrand(@PathVariable("id") String id);

	
	
}
