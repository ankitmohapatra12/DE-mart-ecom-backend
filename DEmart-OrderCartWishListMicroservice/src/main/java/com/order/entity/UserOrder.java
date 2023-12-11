package com.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Document("orders")
//public class UserOrder {
//	
//	
//	
//    private String orderId;
//
//    private LocalDateTime orderDate;
//    
//    private long orderedBy;
//    
//    private String customerName;
//    
//    private String customerEmail;
//    
//    
//    private UserAddress address;
//    // Other fields...
//
//    @DBRef
//    private List<CartItem> cartItems;
//   
//}
