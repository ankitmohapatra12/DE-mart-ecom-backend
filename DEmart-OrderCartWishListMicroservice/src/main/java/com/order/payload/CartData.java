package com.order.payload;

import java.util.List;

import com.order.entity.CartItem;
import com.order.entity.UserAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartData {

	
	private UserAddress address;
	private List<CartItem> cartItems;
	private int totalMrp;
	private int discountOnMrp;
	private int couponDiscount;
	
	private int deliveryCharge;
	
	private int totalAmount;
	
}
