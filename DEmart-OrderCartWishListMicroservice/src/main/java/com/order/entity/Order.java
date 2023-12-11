package com.order.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.order.payload.CartData;
import com.order.payload.UserCreds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("orders")
public class Order {
	
	@Id
	private String orderId;
	private CartData cartData;
	private String paymentType;
	private UserCreds orderedBy;
	private String orderStatus;
	private boolean deleted = false;
	private String paymentStatus;
	private Date orderedDate = new Date();
	
}
