package com.demart.brand.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demart.brand.entity.Brand;
import com.demart.brand.services.BrandService;

@RequestMapping("/brands")
@RestController
public class BrandController {
	
	@Autowired
	private BrandService brandService;

	
	@PostMapping("/add")
	public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) throws Exception {
		return ResponseEntity.ok(brandService.addBrand(brand));
	}
	
	@GetMapping("/all/view")
	public ResponseEntity<List<Brand>> viewProductType() throws Exception  {
		return ResponseEntity.ok(brandService.viewAllBrands());
	}
	
}
