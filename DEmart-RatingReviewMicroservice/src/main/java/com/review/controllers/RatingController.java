package com.review.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.review.entity.RatingReview;
import com.review.entity.ReviewImages;
import com.review.payloads.AvgRatingAndTotalReviews;
import com.review.service.ReviewService;

@RestController
@RequestMapping("/review")
public class RatingController {

	
	@Value("${file.review.images}")
	private String reviewImagePath;
	
	
	@Autowired
	private ReviewService reviewService;
	
	
	public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            // Extract the substring after the last dot
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            // No file extension found
            return "";
        }
    }
	
	
	public boolean checkIfUserReviewExists(RatingReview review) {
		RatingReview checkReview = reviewService.checkIfUserReviewExists(review);
		if(checkReview!=null) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/product/get/{productId}")
	public ResponseEntity<List<RatingReview>> getReviews(@PathVariable String productId) throws Exception {
		List<RatingReview> reviews =  reviewService.viewReviews(Long.parseLong(productId));
		//System.out.println(products.get(0).getSubCategoryProducts().getSubCategoryName());
		return ResponseEntity.ok(reviews);
	}
	
	
	
	@GetMapping("/all/product/{productId}")
	public ResponseEntity<List<RatingReview>> getAllReviews(@PathVariable String productId) throws Exception {
		List<RatingReview> reviews =  reviewService.viewAllReviews(Long.parseLong(productId));
		//System.out.println(products.get(0).getSubCategoryProducts().getSubCategoryName());
		return ResponseEntity.ok(reviews);
	}
	
	
	@GetMapping("/product/getcount/{productId}")
	public ResponseEntity<AvgRatingAndTotalReviews> getReviewsCounts(@PathVariable String productId) throws Exception {
		AvgRatingAndTotalReviews avgRatingReviews =  reviewService.viewReviewsCounts(Long.parseLong(productId));
		//System.out.println(products.get(0).getSubCategoryProducts().getSubCategoryName());
		return ResponseEntity.ok(avgRatingReviews);
	}
	
	@PostMapping(value={"/user/add"},consumes = {"multipart/form-data",MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<RatingReview> addCategory(@RequestPart("files") MultipartFile images[],@RequestPart("review") RatingReview review) throws Exception {
		
		
		if(checkIfUserReviewExists(review)) {
			throw new Exception("User Review already Exists !!");
		}
		else {
			if(review.getReviewId()==null || review.getReviewId()=="") {
				 review.setReviewId(UUID.randomUUID().toString());
			}
			
			boolean flag= true;
			File destPath = null;
			String extension = "";
			String reviewImagename="";
			List<ReviewImages> reviewImageList = new ArrayList<>();
			if(images.length!=0) {
				
				
				//User user = libraryService.addBook(book)
				destPath=new File(reviewImagePath+File.separator+review.getUser().getUserName());
				if(!destPath.exists()){
					destPath.mkdirs();
				}
				int ct = 1;
				for(MultipartFile image : images) {
					ReviewImages reviewImages =  new ReviewImages();
					extension=getFileExtension(image.getOriginalFilename());
					reviewImagename = review.getUser().getUserName()+"_review_image_"+ct+"."+extension;
					FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + reviewImagename));
					ct++;
					reviewImages.setReviewImageName(reviewImagename);
					reviewImages.setImageSize(image.getSize());
					reviewImages.setUploadDate(new Date());
					
					reviewImageList.add(reviewImages);
					
					
					
				}
				
				review.setReviewImages(reviewImageList);
				
				
			}
			System.out.println(review.getReviewImages());
			return ResponseEntity.ok(reviewService.saveReview(review));
		}

	}
	
}
