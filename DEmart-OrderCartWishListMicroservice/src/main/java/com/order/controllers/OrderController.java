package com.order.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.CartItem;
import com.order.entity.Order;
import com.order.payload.CartData;
import com.order.payload.captchaInput;
import com.order.services.CartService;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;

@RestController
@RequestMapping("/cart")
public class OrderController {
	
	
	public CartData cartDataa = new CartData();
	
	@Autowired
	private CartService cartService;
	
	 private Captcha captcha;
	 
	 
	 @PostMapping("/save-order")
	 public Order saveOrder(@RequestBody Order order) {
		 if(order.getOrderId()==null || order.getOrderId()=="") {
			 order.setOrderId(UUID.randomUUID().toString());
		 }
		 
		 Order ordercheck = cartService.saveOrder(order);
		 return ordercheck;
	 }
	 
	 
	 
	 @GetMapping("/orders/{userId}")
	 public List<Order> saveOrder(@PathVariable String userId) {
		 List<Order> ordercheck = cartService.getAllOrder(userId);
		 return ordercheck;
	 }
	 
	 @GetMapping("/orders/all")
	 public List<Order> getAllOrdersAdmin() {
		 List<Order> orders = cartService.getAllOrdersAdmin();
		 return orders;
	 }
	 
	   @GetMapping("/payment/generateCaptcha")
	   public String generateCaptcha() {
		 
		 captcha = new Captcha.Builder(300, 50)
	                .addText(new DefaultTextProducer(6, new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}))
	                .addBackground(new GradiatedBackgroundProducer())
	                .addNoise(new CurvedLineNoiseProducer())
	                .build();
		
	        // Convert BufferedImage to byte array
	        BufferedImage imageData = captcha.getImage();

	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try {
	            ImageIO.write(imageData, "png", baos);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        byte[] imageDataBytes = baos.toByteArray();

	        // Encode the captcha image to base64
	        String base64Image = Base64.getEncoder().encodeToString(imageDataBytes);
//	        System.out.println(base64Image);
	        // Return the base64-encoded captcha image to the frontend
	        return "data:image/png;base64," + base64Image;
	    }
	 
	 
	 @PostMapping("/payment/validateCaptcha")
	 public captchaInput validateCaptcha(@RequestBody captchaInput captchaInput) throws Exception {
//		 System.out.println(captchaInput.getCaptchaInput());
//		 System.out.println(captcha.getAnswer());
	        // Validate user input
	        if (captcha.getAnswer().trim().equalsIgnoreCase(captchaInput.getCaptchaInput().trim())) {
	            return captchaInput;
	        } else {
	            throw new Exception("Captcha Validation failed !!");
	        }
	  }

	
	 
	 private byte[] convertImageToByteArray(BufferedImage image) {
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(image, "png", baos);
	            return baos.toByteArray();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new byte[0];
	        }
	}
	 
	 
	 
	@PostMapping("/add")
	public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) throws Exception{
		cartItem.setItemId(UUID.randomUUID().toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addToCart(cartItem));
	}
	
	
	
	
	@PostMapping("/cartData/add")
	public ResponseEntity<CartData> saveCartDataToJavaObject(@RequestBody CartData cartData){
		System.out.println("ds");
		System.out.println(cartData);
		this.cartDataa = cartData;
		return ResponseEntity.ok(cartData);
	}
	
	@GetMapping("/cartData/get")
    public ResponseEntity<CartData> retrieveCartDataFromJavaObject() {
		System.out.println(cartDataa);
        return ResponseEntity.ok(cartDataa);
    }
	
	
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String userId) throws Exception{
		System.out.println(userId);
		return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart(Long.parseLong(userId)));
	}
	

	@GetMapping("/get/count/{userId}")
	public int getCartItemsCount(@PathVariable String userId) throws Exception{
		return cartService.getCart(Long.parseLong(userId)).size();
	}
	
	
	
	
	
	@GetMapping("/order/cancel/{orderId}")
	public Order cancelOrder(@PathVariable String orderId) throws Exception{
		return cartService.cancelOrder(orderId);
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
