package com.review.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.review.payloads.Product;
import com.review.payloads.UserCreds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ratingreview")
public class RatingReview {
	
	
	@Id
	private String reviewId;
	private int rating;
	private String reviewDescription;
	private String reviewTitle;
	private String reviewerName;
	private Date date = new Date();
	private UserCreds user;
	private Product product;
	private List<ReviewImages> reviewImages;

}
