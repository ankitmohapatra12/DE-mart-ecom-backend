package com.review.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.review.entity.RatingReview;


@Repository
public interface ReviewRepository extends MongoRepository<RatingReview, Long> {
	
	
	@Query("{ 'product.productId' : ?0 , 'user.userId' : ?1 }")
	RatingReview findReviewByUser(long productId, long userId);

	
	
	@Query("{ 'product.productId' : ?0}")
	List<RatingReview> findReviewsByProduct(Long productId,Pageable pageable);
	
	
	@Query("{ 'product.productId' : ?0}")
	List<RatingReview> findAllReviewsByProduct(Long productId);
	
	
	
	

}
