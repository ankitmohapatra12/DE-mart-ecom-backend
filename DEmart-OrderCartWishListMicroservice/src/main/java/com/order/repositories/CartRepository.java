package com.order.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.order.entity.CartItem;
import com.order.entity.UserOrder;


@Repository
public interface CartRepository extends MongoRepository<CartItem, Long> {

	@Query(value = "{'user.userId': ?0}")
	List<CartItem> findByUserUserId(Long userId);

	
	@Query("{ 'product.productId' : ?0 }")
	CartItem findByProductName(long productId);
	
	

}
