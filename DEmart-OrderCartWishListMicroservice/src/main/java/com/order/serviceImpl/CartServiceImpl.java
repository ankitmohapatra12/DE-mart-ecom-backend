package com.order.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.CartItem;
import com.order.repositories.CartRepository;
import com.order.services.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public CartItem addToCart(CartItem cartItem) throws Exception {
		CartItem cartItemExists = new CartItem();
		try {
			cartItemExists = cartRepository.findByProductName(cartItem.getProduct().getProductId());
		}catch (Exception e) {
			throw new Exception("Issue while checking if cart item exists");
		}
		
		if(cartItemExists!=null) {
			throw new Exception("This Item already exists in cart !!");
		}
		return cartRepository.save(cartItem);
	}

	@Override
	public List<CartItem> getCart(Long userId) {
		return cartRepository.findByUserUserId(userId);
	}

	@Override
	public CartItem updateItemQuantiy(CartItem cartItem) throws Exception {
		// TODO Auto-generated method stub
		return cartRepository.save(cartItem);
	}

	@Override
	public void removeFromCart(CartItem cartItem) {
		// TODO Auto-generated method stub
		cartRepository.delete(cartItem);
	}

}
