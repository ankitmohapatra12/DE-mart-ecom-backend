package com.review.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.review.entity.RatingReview;
import com.review.entity.ReviewImages;
import com.review.payloads.AvgRatingAndTotalReviews;
import com.review.repositories.ReviewRepository;
import com.review.service.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService{
	
	
	@Value("${file.review.images}")
	private String reviewImagePath;
	
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public RatingReview saveReview(RatingReview review) {
		// TODO Auto-generated method stub
		return reviewRepository.save(review);
	}

	@Override
	public RatingReview checkIfUserReviewExists(RatingReview review) {
		// TODO Auto-generated method stub
		return reviewRepository.findReviewByUser(review.getProduct().getProductId(),review.getUser().getUserId());
	}

	@Override
	public List<RatingReview> viewReviews(Long productId) throws Exception {
		int page = 0;
		int size = 3;
		String fileSize = "";
		Sort sort = Sort.by(Sort.Direction.ASC, "date");
		Pageable pageable = PageRequest.of(page, size, sort);
		// TODO Auto-generated method stub
		List<RatingReview> reviews=reviewRepository.findReviewsByProduct(productId,pageable);
		if(!reviews.isEmpty()) {
			for(RatingReview review : reviews) {
				File path = new File(reviewImagePath+File.separator+review.getUser().getUserName());
				List<ReviewImages> reviewImages = new ArrayList<>();
				if(path.exists()) {
					File files[]  =  path.listFiles(File::isFile);
					for(File file : files) {
						fileSize = String.valueOf(file.length()/1024);
						ReviewImages image = new ReviewImages();
						image.setImageSize(Integer.parseInt(fileSize));
						String imgUrl = file.toString();
						String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
						if(file.exists()) {
							String  extnValue = "image/" + extension;
							String encodstring = encodeFileToBase64Binary(file);
							String base64Image = "data:" + extnValue + ";base64," + encodstring;
							image.setImgUrl(base64Image);
							image.setReviewImageName(file.getName());
						}
						reviewImages.add(image);
					}
				}
				review.setReviewImages(reviewImages);
				reviewImages = null;
				
			}
		}
		return reviews;
	}
	
	
	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		return new String(java.util.Base64.getEncoder().encodeToString(bytes));
	}

	@Override
	public AvgRatingAndTotalReviews viewReviewsCounts(long productId) {
		// TODO Auto-generated method stub
	    AvgRatingAndTotalReviews avg = new AvgRatingAndTotalReviews();
	    List<RatingReview> ratingReview = reviewRepository.findAllReviewsByProduct(productId);
	    avg.setCount(ratingReview.size());
	    int sum = ratingReview.stream()
	            .mapToInt(RatingReview::getRating) // Assuming getRating returns the rating property
	            .sum();
	    avg.setRating((double)sum/avg.getCount());
	    System.out.println(avg.getRating());
		return avg;
	}

	@Override
	public List<RatingReview> viewAllReviews(long productId) throws Exception {
		String fileSize="";
		// TODO Auto-generated method stub
		List<RatingReview> reviews=reviewRepository.findAll();
		if(!reviews.isEmpty()) {
			for(RatingReview review : reviews) {
				File path = new File(reviewImagePath+File.separator+review.getUser().getUserName());
				List<ReviewImages> reviewImages = new ArrayList<>();
				if(path.exists()) {
					File files[]  =  path.listFiles(File::isFile);
					for(File file : files) {
						fileSize = String.valueOf(file.length()/1024);
						ReviewImages image = new ReviewImages();
						image.setImageSize(Integer.parseInt(fileSize));
						String imgUrl = file.toString();
						String extension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
						if(file.exists()) {
							String  extnValue = "image/" + extension;
							String encodstring = encodeFileToBase64Binary(file);
							String base64Image = "data:" + extnValue + ";base64," + encodstring;
							image.setImgUrl(base64Image);
							image.setReviewImageName(file.getName());
						}
						reviewImages.add(image);
					}
				}
				review.setReviewImages(reviewImages);
				reviewImages = null;
				
			}
		}
		return reviews;
	}

}
