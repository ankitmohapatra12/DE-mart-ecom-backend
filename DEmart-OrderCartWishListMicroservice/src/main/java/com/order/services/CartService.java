package com.order.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.order.entity.CartItem;

@Service
public interface CartService {

	CartItem addToCart(CartItem cartItem) throws Exception;
	
	CartItem updateItemQuantiy(CartItem cartItem) throws Exception;

	List<CartItem> getCart(Long userId);

	void removeFromCart(CartItem cartItem);

}
