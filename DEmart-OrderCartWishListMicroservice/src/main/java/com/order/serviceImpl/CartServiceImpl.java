package com.order.serviceImpl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.order.entity.CartItem;
import com.order.entity.Order;
import com.order.repositories.CartRepository;
import com.order.repositories.OrderRepository;
import com.order.services.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Value("${sender.username}")
	private String senderEmail;
	
	@Value("${sender.password}")
	private String senderEmailPassword;

	@Autowired
	private CartRepository cartRepository;
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public CartItem addToCart(CartItem cartItem) throws Exception {
		CartItem cartItemExists = new CartItem();
		try {
			cartItemExists = cartRepository.findByProductName(cartItem.getProduct().getProductId(),cartItem.getUser().getUserId());
		}catch (Exception e) {
			throw new Exception("Issue while checking if cart item exists");
		}
		
		if(cartItemExists!=null) {
			throw new Exception("This Item already exists in cart !!");
		}
		return cartRepository.save(cartItem);
	}

	
	public String sendNotificationEmail(Order order) {
		String from = senderEmail;
		System.out.println(order.getOrderedBy().getUserEmail());
		
		String to = order.getOrderedBy().getUserEmail();
		Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderEmailPassword);
            }
        });
	    
	    
	    try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Success! Your DEmart Order  #"+order.getOrderId()+" is on its way to you!");

            // Now set the actual message
            Multipart multipart = new MimeMultipart();

            // create and add a text part
            BodyPart textPart = new MimeBodyPart();
            //textPart.setText("Hello Ankit, Thank you for your order. We’ll send a confirmation when your order ships. If you would like to view the status of your order or make any changes to it, please visit Your Orders on DEmart");
            //multipart.addBodyPart(textPart);

            // create and add an HTML part
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<!DOCTYPE html>\r\n"
            		+ "<html lang=\"en\">\r\n"
            		+ "<head>\r\n"
            		+ "    <meta charset=\"UTF-8\">\r\n"
            		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
            		+ "    <title>Document</title>\r\n"
            		+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
            		+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
            		
            		
            		+ "</head>\r\n"
            		+ "<body>\r\n"
            		+ "\r\n"
            		+ "<div class=\"body-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0; padding: 20px;\" bgcolor=\"#f6f6f6\">\r\n"
            		+ "    <div class=\"container\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px;padding:20px; max-width: 800px; margin: 0 auto;\">\r\n"
            		+ "        <div class=\"main\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0;padding:20px; border: 1px solid #e9e9e9;\" bgcolor=\"#fff\">\r\n"
            		+ "            <div class=\"text-white text-center p-4\" style=\"background-color: black;text-align:center;padding:20px;\" >\r\n"
            		+ "                <h2 href=\"#\" class=\"text-white\" style=\"font-size: 32px;color:white;\">DEmart</h2>\r\n"
            		+ "                <span class=\"mt-2 d-block\"></span>\r\n"
            		+ "            </div>\r\n"
            		+ "            \r\n"
            		+ "            <div class=\"content-wrap p-4\"  style=\\\"padding: 40px;width:70%;margin-top:30px;\\\">\r\n"
            		+ "                <div class=\"content-block\" style=\"margin-top:30px;\">\r\n"
            		+ "                    Hi <strong>"+order.getOrderedBy().getUserName()+"</strong>,\r\n"
            		+ "                </div>\r\n"
            		+ "                <div class=\"content-block\" style=\\\\\\\\\\\\\\\"padding: 20px;\\\\\\\\\\\\\\\">\r\n"
            		+ "                    <p>Your DEmart order has been placed successfully..</p>\r\n"
            		+ "                </div>\r\n"
            		+ "                <div class=\"content-block\" style=\\\\\\\\\\\\\\\"padding: 20px;\\\\\\\\\\\\\\\">\r\n"
            		+ "                    <p>Thank you for shopping with us! You have ordered on "+order.getOrderedDate()+". Once packed and shipped, we shall update you on the promised date of delivery.</p>\r\n"
            		+ "                </div>\r\n"
            		+ "                <div class=\"content-block\" style=\\\\\\\\\\\\\\\"padding: 20px;\\\\\\\\\\\\\\\">\r\n"
            		+ "                <p><strong>Meanwhile, stay bold, stay stylish!</strong></p>\r\n"
            		+ "                </div>\r\n"
            		+ "\r\n"
            		+ "                <div class=\"container\" style=\"margin-left: 0px;padding: 20px; border-radius: 10px; background-color: rgb(234, 249, 255);\">\r\n"
            		+ "                    <div class=\"row\" >\r\n"
            		+ "                        <h2>Your order details</h2>\r\n"
            		+ "                        <p style=\"color: gray;\">Order ID  #<span style=\"color: rgb(13, 90, 120);\">"+order.getOrderId()+"</span></p>\r\n"
            		+ "                        <p style=\"color: gray;\">Order placed on <span style=\"color: rgb(0, 0, 0);\">"+order.getOrderedDate()+"</span></p>\r\n"
            		+ "                        <p style=\"color: gray;\">Order will be delivered to <span style=\"color: rgb(0, 0, 0);\">"+order.getOrderedBy().getUserName()+"</span><span></span></p>\r\n"
            		+ "                    </div>\r\n"
            		+ "                </div>\r\n"
            		+ "                \r\n"
            		+ "\r\n"
            		+ "                <div class=\"container\" style=\"margin-left: 0px;padding: 20px; border-radius: 10px; background-color: rgb(234, 249, 255);margin-top: 10px;\">\r\n"
            		
            		+ "                        <div class=\"row\">\r\n"
            		+ "                            <div class=\"col-md-6\"></div>\r\n"
            		+ "                            <div class=\"col-md-6\">\r\n"
            		+ "                                <table style=\"width: 100%;\">\r\n"
            		+ "                                    <tr>\r\n"
            		+ "                                      <td style=\"color: rgb(90, 90, 90);font-size: 12px;\">Total Mrp</td>\r\n"
            		+ "                                      <td style=\"text-align: right;\">"+order.getCartData().getTotalMrp()+"</td>\r\n"
            		+ "                                    </tr>\r\n"
            		+ "                                    <tr>\r\n"
            		+ "                                      <td style=\"color: rgb(90, 90, 90);font-size: 12px;\">Standard Delivery</td>\r\n"
            		+ "                                      <td style=\"text-align: right;\">Rs. "+order.getCartData().getDeliveryCharge()+"</td>\r\n"
            		+ "                                    </tr>\r\n"
            		+ "                                    <tr>\r\n"
            		+ "                                      <td style=\"color: rgb(90, 90, 90);font-size: 12px;\">Coupon Discount</td>\r\n"
            		+ "                                      <td style=\"text-align: right;\">-Rs. "+order.getCartData().getCouponDiscount()+"</td>\r\n"
            		+ "                                    </tr>\r\n"
            		+ "                                    <tr>\r\n"
            		+ "                                      <td style=\"font-size: 15px;font-weight: 800;\">Total Amount</td>\r\n"
            		+ "                                      <td style=\"text-align: right;\">Rs. "+order.getCartData().getTotalAmount()+"</td>\r\n"
            		+ "                                    </tr>\r\n"
            		+ "                                    <tr>\r\n"
            		+ "                                      <td style=\"font-size: 15px;font-weight: 800;\">You Save</td>\r\n"
            		+ "                                      <td style=\"text-align: right;\">Rs. "+(order.getCartData().getTotalMrp() - order.getCartData().getTotalAmount())+"</td>\r\n"
            		+ "                                    </tr>\r\n"
            		+ "                                  </table>\r\n"
            		+ "                            </div>\r\n"
            		+ "                        </div>\r\n"
            		+ "                    </div>\r\n"
            		+ "                    \r\n"
            		+ "                    \r\n"
            		+ "                </div>\r\n"
            		+ "\r\n"
            		+ "\r\n"
            		+ "                <div class=\"container\" style=\"margin-top: 20px;margin-left: 0px;padding: 20px; border-radius: 10px; background-color: rgb(234, 249, 255);\">\r\n"
            		+ "                    <div class=\"row\" >\r\n"
            		+ "                        <div class=\"col-md-7\">\r\n"
            		+ "                            <h4>Delivery Address</h4>\r\n"
            		+ "                            <h6 style=\"padding: 0;\">"+order.getCartData().getAddress().getName()+",<span><b>"+order.getCartData().getAddress().getMobile()+"</b></span></h6>"
            		+ "                            <h6>"+order.getCartData().getAddress().getAddress()+"</h6>\r\n"
            		+ "                            <h6>"+order.getCartData().getAddress().getCity()+"</h6>\r\n"
            		+ "                            <h6>"+order.getCartData().getAddress().getState()+"<span><b> ,"+order.getCartData().getAddress().getCountry()+"</b></span></h6>\r\n"
            		+ "                            <h6>"+order.getCartData().getAddress().getPincode()+"</h6>\r\n"
            		+ "                        </div>\r\n"
            		+ "                        <div class=\"col-md-5\">\r\n"
            		+ "                            <h4>"+order.getPaymentType()+" - "+order.getPaymentStatus()+"</h4>\r\n"
            		+ "                            <h6>Cash on delivery</h6>\r\n"
            		+ "                        </div>\r\n"
            		+ "                    </div>\r\n"
            		+ "                </div>\r\n"
            		+ "                \r\n"
            		+ "                <div class=\"content-block\" style=\"margin-top: 30px;text-align: right;\">\r\n"
            		+ "                    <a href=\"localhost:4200/user-dashboard/profile/orders\" class=\"btn btn-primary\">View your order</a>\r\n"
            		+ "                </div>\r\n"
            		+ "                \r\n"
            		+ "                <div class=\"content-block\" style=\\\\\\\\\\\\\\\"padding: 20px;\\\\\\\\\\\\\\\">\r\n"
            		+ "                    Thanks for choosing <b>DEmart</b>\r\n"
            		+ "                    \r\n"
            		+ "                </div>\r\n"
            		+ "                <div class=\"row\" style=\"margin-top: 20px;padding:20px;\"> \r\n"
            		+ "                    <div class=\"col-md-12\">\r\n"
            		+ "                        <h5> <strong>Please note:</strong></h5>\r\n"
            		+ "                        <p>We do not demand your banking and credit card details verbally or telephonically. Please do not divulge your details to fraudsters and imposters falsely claiming to be calling on our behalf.</p>\r\n"
            		+ "                    </div>\r\n"
            		+ "                </div>\r\n"
            		+ "               \r\n"
            		+ "\r\n"
            		+ "\r\n"
            		+ "            </div>\r\n"
            		+ "\r\n"
            		+ "            <div class=\"footer p-4\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0;background-color: black;\">\r\n"
            		+ "                <div class=\"aligncenter content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; color: #999; text-align: center; margin: 0;\">\r\n"
            		+ "                    <a href=\"#\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; color: #999; text-decoration: underline; margin: 0;\">Unsubscribe</a> from these alerts.\r\n"
            		+ "                </div>\r\n"
            		+ "            </div>\r\n"
            		+ "        </div>\r\n"
            		+ "    </div>\r\n"
            		+ "</div>\r\n"
            		+ "\r\n"
            		+ "</body>\r\n"
            		+ "</html>", "text/html");
            multipart.addBodyPart(htmlPart);

            // set the content of the message to the MimeMultipart
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	    
		return "success";
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

	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		System.out.println(order.getCartData());
		Order checkOrder = orderRepository.save(order);
		List<CartItem> cartItemsDeleted = cartRepository.deleteByUserUserId(order.getOrderedBy().getUserId());
		String result = sendNotificationEmail(checkOrder);
		System.out.println(cartItemsDeleted.size());
		return checkOrder;
	}

	@Override
	public List<Order> getAllOrder(String userId) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByOrderBy(Long.parseLong(userId));
	}

	@Override
	public Order cancelOrder(String orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.cancelOrder(orderId);
		if (order != null) {
		    order.setDeleted(true);
		    orderRepository.save(order);
		}
		
		return order;
	}


	@Override
	public List<Order> getAllOrdersAdmin() {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepository.findAll();
		if(orders.isEmpty()) {
			return null;
		}
		return orders;
	}

}
