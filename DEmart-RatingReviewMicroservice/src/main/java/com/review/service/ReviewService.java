package com.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.review.entity.RatingReview;
import com.review.payloads.AvgRatingAndTotalReviews;

@Service
public interface ReviewService {

	RatingReview saveReview(RatingReview review);

	RatingReview checkIfUserReviewExists(RatingReview review);

	List<RatingReview> viewReviews(Long productId) throws Exception;

	AvgRatingAndTotalReviews viewReviewsCounts(long productId);

	List<RatingReview> viewAllReviews(long productId) throws Exception;
	
	
}
