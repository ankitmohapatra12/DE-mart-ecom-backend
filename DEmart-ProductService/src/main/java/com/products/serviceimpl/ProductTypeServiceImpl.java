package com.products.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.products.dto.Brand;
import com.products.dto.Seller;
import com.products.entity.Category;
import com.products.entity.CategoryImages;
import com.products.entity.Product;
import com.products.entity.ProductImages;
import com.products.entity.ProductType;
import com.products.entity.SubCategory;
import com.products.entity.SubCategoryImages;
import com.products.exception.ProductTypeFailedException;
import com.products.externalservices.BrandService;
import com.products.externalservices.SellerService;
import com.products.repositories.CategoryRepository;
import com.products.repositories.ProductRepository;
import com.products.repositories.ProductTypeRepository;
import com.products.repositories.SubCategoryRepository;
import com.products.services.ProductTypeService;


@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	
	@Value("${file.category.images}")
	private String categoryImagePath;
	
	@Value("${file.subCategory.images}")
	private String subCategoryImagePath;
	
	@Value("${file.product.images}")
	private String productImagePath;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandService brandService;
	
	
	@Autowired
	private SellerService sellerService;
	
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	

	@Override
	public ProductType addProductType(ProductType productType) throws ProductTypeFailedException {
		
		if(!productType.getProductType().equals("") || productType.getProductType()!=null) {
			if(!productType.getProductTypeDescription().equals("") || productType.getProductTypeDescription()!=null) {
				productType = productTypeRepository.saveAndFlush(productType);
			}
			else {
				throw new ProductTypeFailedException("Failed to add product type !! (product type description is blank");
			}
		}
		else {
			throw new ProductTypeFailedException("Failed to add product type !! (product type is blank");
		}
		
		return productType;
	}


	@Override
	public List<ProductType> viewProductType() {
		// TODO Auto-generated method stub
		return productTypeRepository.findAll();
	}


	@Override
	public Category addCategory(Category category) {

	    category = categoryRepository.saveAndFlush(category);
		return category;
	}


	@SuppressWarnings("unused")
	@Override
	public List<Category> viewCategories() throws Exception {
		// TODO Auto-generated method stub
		String fileSize = "";
		List<Category> categories =  categoryRepository.findAll();
		//Map<String,List<String>> categoryImagesData = new HashMap<>();
		//List<String> categories_updated = new ArrayList<>();
		
		
		if(!categories.isEmpty()) {
			for(Category category : categories) {
				File path = new File(categoryImagePath+File.separator+category.getCategoryName());
				List<CategoryImages> categoryImages = new ArrayList<>();
							//JSONObject jsonObj =  new JSONObject();
				
				if(path.exists()) {
					File files[]  =  path.listFiles(File::isFile);
					
					
					for(File file : files) {
						fileSize = String.valueOf(file.length()/1024);
						CategoryImages image = new CategoryImages();
						image.setImageSize(Integer.parseInt(fileSize));
						String imgUrl = file.toString();
					    
						String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
						if(file.exists()) {
							String  extnValue = "image/" + extension;
							String encodstring = encodeFileToBase64Binary(file);
							String base64Image = "data:" + extnValue + ";base64," + encodstring;
							image.setImgUrl(base64Image);
							image.setCategoryImageName(file.getName());
						
						}
						
						categoryImages.add(image);
					}
					
					
					
					
					
				}
				category.setCategoryImages(categoryImages);
				categoryImages = null;
				
			}
			
			
		}
		
		
		
		return categories;
	}
	
	

	
	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		return new String(java.util.Base64.getEncoder().encodeToString(bytes));
	}


	@Override
	public SubCategory addSubCategory(SubCategory subCategory) {
		subCategory = subCategoryRepository.saveAndFlush(subCategory);
		return subCategory;
	}


	@Override
	public List<SubCategory> viewSubCategories() throws Exception {
		// TODO Auto-generated method stub
		String fileSize = "";
		List<SubCategory> subCategories = subCategoryRepository.findAll();
		//Map<String,List<String>> categoryImagesData = new HashMap<>();
		//List<String> categories_updated = new ArrayList<>();
		
		
		if(!subCategories.isEmpty()) {
			for(SubCategory subCategory : subCategories) {
				File path = new File(subCategoryImagePath+File.separator+subCategory.getSubCategoryName());
				List<SubCategoryImages> subCategoryImages = new ArrayList<>();
							//JSONObject jsonObj =  new JSONObject();
				
				if(path.exists()) {
					File files[]  =  path.listFiles(File::isFile);
					
					
					for(File file : files) {
						fileSize = String.valueOf(file.length()/1024);
						SubCategoryImages image = new SubCategoryImages();
						image.setImageSize(Integer.parseInt(fileSize));
						String imgUrl = file.toString();
					    
						String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
						if(file.exists()) {
							String  extnValue = "image/" + extension;
							String encodstring = encodeFileToBase64Binary(file);
							String base64Image = "data:" + extnValue + ";base64," + encodstring;
							image.setImgUrl(base64Image);
							image.setSubCategoryImageName(file.getName());
						
						}
						
						subCategoryImages.add(image);
					}
					
					
					
					
					
				}
				subCategory.setSubCategoryImages(subCategoryImages);
				subCategoryImages = null;
				
			}
			
			
		}
		
		
		
		return subCategories;
	}


	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.saveAndFlush(product);
	}
	
	
	


	@Override
	public List<Product> viewProducts() throws Exception {
		String fileSize = "";
		List<Product> products = productRepository.findAll();
		List<Brand> brands = brandService.getBrands();
		List<Seller> sellers = sellerService.getSellers();
		
		for(Product product : products) {
			Brand brand = brands.stream().filter((x) -> x.getId() == product.getBrandId()).findFirst().orElse(null);
			Seller seller = sellers.stream().filter((x) -> x.getSellerId() == product.getSellerId()).findFirst().orElse(null);
			product.setBrand(brand);
			product.setSeller(seller);
			
		}
		
		//Map<String,List<String>> categoryImagesData = new HashMap<>();
		//List<String> categories_updated = new ArrayList<>();
		
		
		if(!products.isEmpty()) {
			for(Product product : products) {
				File path = new File(productImagePath+File.separator+product.getProductName());
				List<ProductImages> productImages = new ArrayList<>();
							//JSONObject jsonObj =  new JSONObject();
				
				if(path.exists()) {
					File files[]  =  path.listFiles(File::isFile);
					
					
					for(File file : files) {
						fileSize = String.valueOf(file.length()/1024);
						ProductImages image = new ProductImages();
						image.setImageSize(Integer.parseInt(fileSize));
						String imgUrl = file.toString();
					    
						String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
						if(file.exists()) {
							String  extnValue = "image/" + extension;
							String encodstring = encodeFileToBase64Binary(file);
							String base64Image = "data:" + extnValue + ";base64," + encodstring;
							image.setImgUrl(base64Image);
							image.setProductImageName(file.getName());
						
						}
						
						productImages.add(image);
					}
					
					
					
					
					
				}
				product.setProductImages(productImages);
				productImages = null;
				
			}
			
			
		}
		
		
		
		return products;
	}





	@Override
	public List<Brand> getAllBrands() {
		// TODO Auto-generated method stub
		return brandService.getBrands();
	}


	

}
