package com.order.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.CartItem;


@Repository
@Transactional
public interface CartRepository extends MongoRepository<CartItem, Long> {

	@Query(value = "{'user.userId': ?0}")
	List<CartItem> findByUserUserId(Long userId);
	
	
	
	List<CartItem>  deleteByUserUserId(Long userId);

	
	@Query("{ 'product.productId' : ?0 , 'user.userId' : ?1 }")
	CartItem findByProductName(long productId,long userId);
	
	

}
