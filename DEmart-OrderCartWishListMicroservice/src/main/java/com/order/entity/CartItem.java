package com.order.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.order.payload.Product;
import com.order.payload.UserCreds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("cart_items")
public class CartItem {
	
	@Id
	 private String itemId; // Use String or ObjectId as ID type
	 
	 @Transient
	 private long productId;
	 private Product product;
	 private int quantity;
	 private UserCreds user;
	 
	 
}
