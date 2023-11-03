package com.demart.brand.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demart.brand.entity.Brand;
import com.demart.brand.repositories.BrandRepository;
import com.demart.brand.services.BrandService;


@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	
	@Override
	public Brand addBrand(Brand brand) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			brand =  brandRepository.saveAndFlush(brand);
		}catch (Exception e) {
			System.out.println("addBrand() : BrandServiceImpl"+e);
			throw new Exception("Failed to add brand to database !");
		}
		return brand;
	}


	@Override
	public List<Brand> viewAllBrands() throws Exception {
		// TODO Auto-generated method stub
		List<Brand> brands = new ArrayList<Brand>();
		try {
			brands = brandRepository.findAll();
		}catch (Exception e) {
			System.out.println("viewAllBrands() : BrandServiceImpl :: Failed to fetch brands");
			throw new Exception("Unable to fetch brands !!");
		}
		
		
		return brands;
	}


	@Override
	public Brand viewBrands(long id) {
		// TODO Auto-generated method stub
		return brandRepository.findById(id).get();
	}

}
