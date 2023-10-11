package com.products.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.entity.CategoryImages;
import com.products.repositories.CategoryImageRepository;
import com.products.services.CategoryImageService;


@Service
public class CategoryImageServiceImpl implements CategoryImageService {
	
	
	@Autowired
	private CategoryImageRepository categoryImageService;

	@Override
	public List<CategoryImages> addAllCategoryImages(List<CategoryImages> images) {
		// TODO Auto-generated method stub
		return categoryImageService.saveAllAndFlush(images);
	}

}
