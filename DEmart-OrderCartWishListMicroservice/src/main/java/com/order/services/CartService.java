package com.order.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order.entity.CartItem;
import com.order.entity.Order;

@Service
public interface CartService {

	CartItem addToCart(CartItem cartItem) throws Exception;
	
	CartItem updateItemQuantiy(CartItem cartItem) throws Exception;

	List<CartItem> getCart(Long userId);

	void removeFromCart(CartItem cartItem);

	Order saveOrder(Order order);

	List<Order> getAllOrder(String userId);

	Order cancelOrder(String orderId);

	List<Order> getAllOrdersAdmin();

}
