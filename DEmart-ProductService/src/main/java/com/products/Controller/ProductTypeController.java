package com.products.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.products.dto.Brand;
import com.products.entity.Category;
import com.products.entity.CategoryImages;
import com.products.entity.Product;
import com.products.entity.ProductImages;
import com.products.entity.ProductType;
import com.products.entity.SubCategory;
import com.products.entity.SubCategoryImages;
import com.products.exception.ProductTypeFailedException;
import com.products.externalservices.BrandService;
import com.products.services.ProductTypeService;

@RequestMapping("/product-type")
@RestController
public class ProductTypeController {
	
	
	@Value("${file.category.images}")
	private String categoryImagePath;
	
	
	@Value("${file.product.images}")
	private String productImagePath;
	
	@Value("${file.subCategory.images}")
	private String subCategoryImagePath;
	
	 private final BrandService brandService;

	    @Autowired
	    public ProductTypeController(BrandService brandService) {
	        this.brandService = brandService;
	    }
	
	
	@Autowired
	private ProductTypeService productTypeService;
	
	
	

	@PostMapping("/add")
	public ResponseEntity<ProductType> addProductType(@RequestBody ProductType productType) throws ProductTypeFailedException {
		return ResponseEntity.ok(productTypeService.addProductType(productType));
	}
	
	
	@GetMapping("/all/view")
	public ResponseEntity<List<ProductType>> viewProductType() throws ProductTypeFailedException {
		return ResponseEntity.ok(productTypeService.viewProductType());
	}
	
	public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            // Extract the substring after the last dot
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            // No file extension found
            return "";
        }
    }
	
	
	@PostMapping(value={"/category/add"},consumes = {"multipart/form-data",MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Category> addCategory(@RequestPart("files") MultipartFile images[],@RequestPart("category") Category category) throws ProductTypeFailedException, IOException {
		System.out.println(images[0]+"\n"+images[1]);
		boolean flag= true;
		File destPath = null;
		String extension = "";
		String categoryImagename="";
		Category category_check = new Category();
		
		List<CategoryImages> categoryImageList = new ArrayList<>();
		
		if(images.length!=0) {
			
			
				//User user = libraryService.addBook(book)
				destPath=new File(categoryImagePath+File.separator+category.getCategoryName());
				if(!destPath.exists()){
					destPath.mkdirs();
				}
				int ct = 1;
				for(MultipartFile image : images) {
					CategoryImages categoryImages =  new CategoryImages();
					extension=getFileExtension(image.getOriginalFilename());
					System.out.println(extension);
					categoryImagename = category.getCategoryName()+"_"+ct+"."+extension;
					System.out.println(categoryImagename);
					FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + categoryImagename));
					ct++;
					categoryImages.setCategoryImageName(categoryImagename);
					categoryImages.setCategory(category);
					categoryImages.setImageSize(image.getSize());
					categoryImages.setUploadDate(new Date());
					
					categoryImageList.add(categoryImages);
					
					
					
				}
				
				category.setCategoryImages(categoryImageList);
			    
//				categoryImageService.addAllCategoryImages(categoryImageList);
				
				
		} 
		
		
	
		return ResponseEntity.ok(productTypeService.addCategory(category));
	}
	
	
	@GetMapping("/categories/view")
	public ResponseEntity<List<Category>> viewCategories() throws ProductTypeFailedException,Exception {
		List<Category> categories =  productTypeService.viewCategories();
		//System.out.println(categories.get(0).getCategoryImages());
		return ResponseEntity.ok(categories);
	}
	
	
	
	@PostMapping(value={"/category/sub-category/add"},consumes = {"multipart/form-data",MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SubCategory> addSubCategory(@RequestPart("files") MultipartFile images[],@RequestPart("subCategory") SubCategory subCategory) throws ProductTypeFailedException, IOException {
		//System.out.println(xyz.toString());
		System.out.println(subCategory.getCategory_sub());
		boolean flag= true;
		File destPath = null;
		String extension = "";
		String subCategoryImagename="";
		Category category_check = new Category();
		
		List<SubCategoryImages> subCategoryImageList = new ArrayList<>();
		
		if(images.length!=0) {
			
			
				//User user = libraryService.addBook(book)
				destPath=new File(subCategoryImagePath+File.separator+subCategory.getSubCategoryName());
				if(!destPath.exists()){
					destPath.mkdirs();
				}
				int ct = 1;
				for(MultipartFile image : images) {
					SubCategoryImages categoryImages =  new SubCategoryImages();
					extension=getFileExtension(image.getOriginalFilename());
					//System.out.println(extension);
					subCategoryImagename = subCategory.getSubCategoryName()+"_"+ct+"."+extension;
					//System.out.println(subCategoryImagename);
					FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + subCategoryImagename));
					ct++;
					categoryImages.setSubCategoryImageName(subCategoryImagename);
					categoryImages.setSubCategory(subCategory);
					categoryImages.setImageSize(image.getSize());
					categoryImages.setUploadDate(new Date());
					
					subCategoryImageList.add(categoryImages);
					
					
					
				}
				
				subCategory.setSubCategoryImages(subCategoryImageList);
			    
//				categoryImageService.addAllCategoryImages(categoryImageList);
				
				
		} 
		
		
	
		return ResponseEntity.ok(productTypeService.addSubCategory(subCategory));
	}
	
	
	@GetMapping("/categories/sub-categories/view")
	public ResponseEntity<List<SubCategory>> viewSubCategories() throws ProductTypeFailedException,Exception {
		List<SubCategory> categories =  productTypeService.viewSubCategories();
//		System.out.println(categories.get(0).getCategoryImages());
		return ResponseEntity.ok(categories);
	}
	
	
	@GetMapping("/brands")
	public ResponseEntity<List<Brand>> viewBrands() throws ProductTypeFailedException,Exception {
		List<Brand> brands =  brandService.getBrands();
		
//		System.out.println(categories.get(0).getCategoryImages());
		return ResponseEntity.ok(brands);
	}
	
	
	
	@PostMapping(value={"/category/sub-category/product/add"},consumes = {"multipart/form-data",MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Product> addProduct(@RequestPart("files") MultipartFile images[],@RequestPart("product") Product product) throws ProductTypeFailedException, IOException {
		
		
		boolean flag= true;
		File destPath = null;
		String extension = "";
		String productImagename="";
		Category category_check = new Category();
		
		List<ProductImages> productImageList = new ArrayList<>();
		
		if(images.length!=0) {
			
			
				//User user = libraryService.addBook(book)
				destPath=new File(productImagePath+File.separator+product.getProductName());
				if(!destPath.exists()){
					destPath.mkdirs();
				}
				int ct = 1;
				for(MultipartFile image : images) {
					ProductImages productImages =  new ProductImages();
					extension=getFileExtension(image.getOriginalFilename());
					//System.out.println(extension);
					productImagename = product.getProductName()+"_"+ct+"."+extension;
					//System.out.println(subCategoryImagename);
					FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + productImagename));
					ct++;
					productImages.setProductImageName(productImagename);
					productImages.setProduct(product);
					productImages.setImageSize(image.getSize());
					productImages.setUploadDate(new Date());
					
					productImageList.add(productImages);
					
					
					
				}
				
				product.setProductImages(productImageList);
				
				
		} 
		
		
	

		return ResponseEntity.ok(productTypeService.addProduct(product));
	}
	
	
	@GetMapping("/categories/sub-categories/products/view")
	public ResponseEntity<List<Product>> viewProducts() throws ProductTypeFailedException,Exception {
		List<Product> products =  productTypeService.viewProducts();
		//System.out.println(products.get(0).getSubCategoryProducts().getSubCategoryName());
		return ResponseEntity.ok(products);
	}
	
	

	
}
