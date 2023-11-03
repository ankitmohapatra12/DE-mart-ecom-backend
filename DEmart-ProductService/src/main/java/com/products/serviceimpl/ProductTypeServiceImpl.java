package com.products.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.products.dto.Brand;
import com.products.dto.DiscountRange;
import com.products.dto.Filters;
import com.products.dto.Manufacturer;
import com.products.dto.PriceRange;
import com.products.dto.Seller;
import com.products.entity.Category;
import com.products.entity.CategoryImages;
import com.products.entity.Product;
import com.products.entity.Property;
import com.products.entity.ProductImages;
import com.products.entity.ProductType;
import com.products.entity.SubCategory;
import com.products.entity.SubCategoryImages;
import com.products.exception.ProductTypeFailedException;
import com.products.externalservices.BrandService;
import com.products.externalservices.ManufacturerService;
import com.products.externalservices.SellerService;
import com.products.repositories.CategoryRepository;
import com.products.repositories.ProductRepository;
import com.products.repositories.PropertyRepository;
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
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PropertyRepository propertyRepo;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandService brandService;
	
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	
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
	public List<ProductType> viewProductType() throws Exception {
		// TODO Auto-generated method stub
		List<ProductType> productTypes = productTypeRepository.findAll();
		
		String fileSize = "";
		int len = productTypes.size();
		//Map<String,List<String>> categoryImagesData = new HashMap<>();
		//List<String> categories_updated = new ArrayList<>();
		
		
		if(!productTypes.isEmpty()) {
			for(ProductType productType : productTypes) {
				for(Category category :productType.getCategories()) {
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
			
			
		}
		
		
		
		
		return productTypes;
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
		List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
		
		for(Product product : products) {
			Brand brand = brands.stream().filter((x) -> x.getId() == product.getBrandId()).findFirst().orElse(null);
			Seller seller = sellers.stream().filter((x) -> x.getSellerId() == product.getSellerId()).findFirst().orElse(null);
			Manufacturer manufacturer = manufacturers.stream().filter((x) -> x.getManufacturerId() == product.getManufacturerId()).findFirst().orElse(null);
			product.setBrand(brand);
			product.setSeller(seller);
			product.setManufacturer(manufacturer);
			
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


	@Override
	public List<Product> viewSellerProducts(long sellerId) throws Exception {
		String fileSize = "";
		List<Product> products = productRepository.findAll();
		List<Brand> brands = brandService.getBrands();
		List<Seller> sellers = sellerService.getSellers();
		List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
		
		List<Product> productsSeller = new ArrayList<>();
		
		for(Product product : products) {
			if(product.getSellerId() == sellerId) {
				Brand brand = brands.stream().filter((x) -> x.getId() == product.getBrandId()).findFirst().orElse(null);
				Seller seller = sellers.stream().filter((x) -> x.getSellerId() == product.getSellerId()).findFirst().orElse(null);
				Manufacturer manufacturer = manufacturers.stream().filter((x) -> x.getManufacturerId() == product.getManufacturerId()).findFirst().orElse(null);
				product.setBrand(brand);
				product.setSeller(seller);
				product.setManufacturer(manufacturer);
				productsSeller.add(product);
			}
		
			
		}
		
		//Map<String,List<String>> categoryImagesData = new HashMap<>();
		//List<String> categories_updated = new ArrayList<>();
		
		
		if(!productsSeller.isEmpty()) {
			for(Product product : productsSeller) {
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
		
		
		
		return productsSeller;
	}


	@Override
	public SubCategory viewSubCategory(long subCategoryId) {
		// TODO Auto-generated method stub
		return subCategoryRepository.findBySubCategoryId(subCategoryId);
	}


	@Override
	public List<Product> getProductsBasedOnSubCategory(long subCategoryId,PageRequest pageRequest,Filters filters) throws Exception {
		String formattedList = "(" + String.join(", ", filters.getSubCategories().stream().map(Object::toString).collect(Collectors.toList())) + ")";
		String formattedBrandList = "(" + String.join(", ", filters.getBrands().stream().map(Object::toString).collect(Collectors.toList())) + ")";
		String fileSize="";
		int page = pageRequest.getPageNumber();
		int size = pageRequest.getPageSize();
		int offset = (page - 1) * size;
		String jpqlQuery="";

		if(filters.getSubCategories().size()>0) {
			jpqlQuery += "SELECT DISTINCT p.* FROM product p JOIN property pp ON p.product_id = pp.product_property_product_id WHERE p.sub_category_products_sub_category_id IN "+formattedList;
		}
		
		if(filters.getSubCategories().size()==0) {
			jpqlQuery += "SELECT DISTINCT p.* FROM product p JOIN property pp ON p.product_id = pp.product_property_product_id WHERE p.sub_category_products_sub_category_id = "+subCategoryId;
		}
		
		

		
		if(filters.getGenders().size()>0 || filters.getColors().size()>0) {
			jpqlQuery +=" AND (";
		}
		
		
		if (filters.getColors().size()>0) {
			String formattedString = "(" + filters.getColors().stream()
            .map(color -> "'" + color.toLowerCase() + "'")
            .collect(Collectors.joining(", ")) + ")";
	        jpqlQuery += "LOWER(pp.property_name) = 'color' AND LOWER(pp.property_value) IN "+formattedString;
	    }
		
		if(filters.getGenders().size()>0 && filters.getColors().size()>0) {
			 jpqlQuery += " OR ";
		}
		
		if (filters.getGenders().size()>0) {
			String formattedString = "(" + filters.getGenders().stream()
		            .map(gender -> "'" + gender.toLowerCase() + "'")
		            .collect(Collectors.joining(", ")) + ")";
	        jpqlQuery += "LOWER(pp.property_name) = 'gender' AND LOWER(pp.property_value) IN "+formattedString;
	    }
		
		if(filters.getGenders().size()>0 || filters.getColors().size()>0) {
			jpqlQuery +=")";
		}
		
		
		
		if (filters.getRating()>0) {
			jpqlQuery += " AND CAST(p.rating AS INTEGER) > "+filters.getRating();
		}
		
		if (filters.getBrands().size()>0) {
			jpqlQuery += " AND p.brand_id IN "+formattedBrandList;
		}
		
		
		if (filters.getMax_discount()>0 && filters.getMin_discount()>0) {
			jpqlQuery += " AND p.discounts BETWEEN "+filters.getMin_discount()+" AND "+filters.getMax_discount();
		}
		
		
		if (filters.getMax_price()>0 && filters.getMin_price()>0) {
			jpqlQuery += " AND CAST(p.discounted_price AS DECIMAL) BETWEEN "+filters.getMin_price()+" AND "+filters.getMax_price();
		}
		
		
		
		if (filters.getSubCategories().size()>0) {
			
	        jpqlQuery += " AND p.sub_category_products_sub_category_id IN (SELECT s.sub_category_id FROM sub_category s WHERE s.sub_category_id IN "+formattedList+")";
	    }
		
		if (filters.getSubCategories().size()==0) {
			
			
	        jpqlQuery += " AND p.sub_category_products_sub_category_id IN (SELECT s.sub_category_id FROM sub_category s WHERE s.sub_category_id = "+subCategoryId+")";
	    }
		
		
		if(filters.getSubCategories().size()>0) {
			jpqlQuery += " LIMIT LEAST("+size+", (SELECT COUNT(*) FROM product WHERE sub_category_products_sub_category_id IN "+formattedList+") - "+offset+") ";

			jpqlQuery += " OFFSET CASE WHEN "+offset+" < (SELECT COUNT(*) FROM product WHERE sub_category_products_sub_category_id IN "+formattedList+") THEN "+offset+" ELSE 0 END";
		}
		
		if(filters.getSubCategories().size()==0) {
		jpqlQuery += " LIMIT LEAST("+size+", (SELECT COUNT(*) FROM product WHERE sub_category_products_sub_category_id = "+subCategoryId+") - "+offset+") ";

		jpqlQuery += " OFFSET CASE WHEN "+offset+" < (SELECT COUNT(*) FROM product WHERE sub_category_products_sub_category_id = "+subCategoryId+") THEN "+offset+" ELSE 0 END";
		
		}
		
		
		
		


//		List<Product> products = productRepository.getProductsBasedOnCategory(jpqlQuery);
		List<Product> products = jdbcTemplate.query(jpqlQuery, new BeanPropertyRowMapper<>(Product.class));
		
		
		
		for(Product product :products) {
			Brand brand = brandService.getBrand(String.valueOf(product.getBrandId()));
			Seller seller = sellerService.getSeller(String.valueOf(product.getSellerId()));
			List<Property> properties = propertyRepo.findByProductProperty(product);
//			String subCatName = subCategoryRepository.findSubCategoryByProductSubCategoryId(product.getProductId());
			product.setProductProperty(properties);
			Manufacturer manufacturer = manufacturerService.getManufacturer(String.valueOf(product.getManufacturerId()));
			product.setBrand(brand);
			product.setSeller(seller);
			product.setManufacturer(manufacturer);
//			SubCategory subCategory = new SubCategory();
//			subCategory.setSubCategoryName(subCatName);
//			product.setSubCategoryProducts(subCategory);
			
		}
		
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
	public List<Product> viewProductsBySubCategory(long subCategoryId) {
		// TODO Auto-generated method stub
		return productRepository.getProductsBasedOnSubCategoryId(subCategoryId);
	}


	@Override
	public Product viewProduct(int productId) throws Exception {
		String fileSize ="";
		// TODO Auto-generated method stub
		Product product = productRepository.findByProductId(productId);
		if(product!=null) {
			
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
			
		Brand brand = brandService.getBrand(String.valueOf(product.getBrandId()));
		Seller seller = sellerService.getSeller(String.valueOf(product.getSellerId()));
		List<Property> properties = propertyRepo.findByProductProperty(product);
		product.setProductProperty(properties);
		Manufacturer manufacturer = manufacturerService.getManufacturer(String.valueOf(product.getManufacturerId()));
		product.setBrand(brand);
		product.setSeller(seller);
		product.setManufacturer(manufacturer);
		return product;
	}


	

}
