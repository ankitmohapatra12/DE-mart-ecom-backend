package com.order.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.Order;


@Repository
@Transactional
public interface OrderRepository extends MongoRepository<Order, Long> {

	
	@Query(value = "{'orderedBy.userId': ?0}")
	List<Order> findAllByOrderBy(long parseInt);

	@Query("{'_id': ?0}")
	Order cancelOrder(String orderId);
	
	
}
