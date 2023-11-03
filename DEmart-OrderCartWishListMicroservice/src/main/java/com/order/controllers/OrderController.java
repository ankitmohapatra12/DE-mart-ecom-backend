package com.order.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.CartItem;
import com.order.services.CartService;

@RestController
@RequestMapping("/cart")
public class OrderController {
	
	
	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/add")
	public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) throws Exception{
		cartItem.setItemId(UUID.randomUUID().toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(cartItem));
	}
	
	
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String userId) throws Exception{
		System.out.println(userId);
		return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart(Long.parseLong(userId)));
	}
	
	
	@PutMapping("/get/update-quantity")
	public ResponseEntity<CartItem> getCartItems(@RequestBody CartItem cartItem) throws Exception{
	
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.updateItemQuantiy(cartItem));
	}
	
	
	@PostMapping("/get/delete")
	public ResponseEntity<CartItem> removeCartItem(@RequestBody CartItem cartItem) throws Exception{
		cartService.removeFromCart(cartItem);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
	}
}
