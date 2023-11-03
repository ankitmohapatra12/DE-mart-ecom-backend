package com.seller.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seller.entity.SellerImages;
import com.seller.entity.Sellers;
import com.seller.service.SellerService;




@RequestMapping("/sellers")
@RestController
public class SellerController {
	
	@Value("${file.seller.images}")
	private String sellerImagePath;
	
	
	@Value("${file.seller.imageData}")
	private String sellerImageDataPath;
	
	@Autowired
	private SellerService sellerService;

	
	@PostMapping(value={"/add"},consumes = {"multipart/form-data",MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Sellers> addSeller(@RequestPart("seller-image-data") MultipartFile seller_picture,@RequestPart("files") MultipartFile images[],@RequestPart("seller") Sellers seller) throws Exception {
		System.out.println(seller_picture.getOriginalFilename());
		System.out.println(images[0].getOriginalFilename());
		System.out.println(seller.getSellerName());
		boolean flag= true;
		File destPath = null;
		File destPath2 = null;
		String extension = "";
		String sellerImageDataname="";
		
		
		List<SellerImages> sellerImageList = new ArrayList<>();
		
		if(images.length!=0) {
			
			
				//User user = libraryService.addBook(book)
				destPath=new File(sellerImagePath+File.separator+seller.getSellerName());
				if(!destPath.exists()){
					destPath.mkdirs();
				}
				
				destPath2=new File(sellerImageDataPath+File.separator+seller.getSellerName());
				if(!destPath2.exists()){
					destPath2.mkdirs();
				}
				int ct = 1;
				for(MultipartFile image : images) {
					SellerImages sellerImages =  new SellerImages();
					extension=getFileExtension(image.getOriginalFilename());
					System.out.println(extension);
					sellerImageDataname = seller.getSellerName()+"_data"+"_"+ct+"."+extension;
					System.out.println(sellerImageDataname);
					FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + sellerImageDataname));
					ct++;
					sellerImages.setSellerImageName(sellerImageDataname);
					sellerImages.setSeller_id(seller);
					sellerImages.setSellerPictureType("Data");
					sellerImages.setImageSize(image.getSize());
					sellerImages.setUploadDate(new Date());
					
					sellerImageList.add(sellerImages);
					
					
					
				}
				
				
				SellerImages sellerImages =  new SellerImages();
				extension=getFileExtension(seller_picture.getOriginalFilename());
				System.out.println(extension);
				sellerImageDataname = seller.getSellerName()+"_Profile"+"_"+ct+"."+extension;
				System.out.println(sellerImageDataname);
				FileCopyUtils.copy(seller_picture.getBytes(),new File(destPath2 + File.separator + sellerImageDataname));
				ct++;
				sellerImages.setSellerImageName(sellerImageDataname);
				sellerImages.setSeller_id(seller);
				sellerImages.setSellerPictureType("Profile");
				sellerImages.setImageSize(seller_picture.getSize());
				sellerImages.setUploadDate(new Date());
				
				sellerImageList.add(sellerImages);
				
				
				
				seller.setImages(sellerImageList);
			    

				
				
		} 
		
		
		return ResponseEntity.ok(sellerService.addSeller(seller));
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
	
	@GetMapping("/all/view")
	public ResponseEntity<List<Sellers>> viewSellers() throws Exception  {
		
		List<Sellers> sellers = sellerService.viewSellers();
		
		System.out.println(sellers.toString());
		
		return ResponseEntity.ok(sellers);
	}
	
	
	@GetMapping("/all/view/{userEmail}")
	public ResponseEntity<Sellers> getSeller(@PathVariable String userEmail) throws Exception  {
		
		Sellers seller = sellerService.getSeller(userEmail);
		
		
		
		return ResponseEntity.ok(seller);
	}
	
	
	@GetMapping("/view/{sellerId}")
	public ResponseEntity<Sellers> getSellerById(@PathVariable String sellerId) throws Exception  {
		
		Sellers seller = sellerService.getSellerById(Long.parseLong(sellerId));
		
		
		
		return ResponseEntity.ok(seller);
	}
}
