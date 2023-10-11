package com.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.products.entity.CategoryImages;

@Service
public interface CategoryImageService {

	
	public List<CategoryImages> addAllCategoryImages(List<CategoryImages> images);
}
