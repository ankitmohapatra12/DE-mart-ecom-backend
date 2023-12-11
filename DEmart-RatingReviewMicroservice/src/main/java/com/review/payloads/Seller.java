package com.review.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

private long sellerId;
	
	private String sellerName;
	
	private String sellerEmail;
	
	private String sellerPhone;
	
	private String sellerAddress;
	
	private String sellerWebsite;
	
	private String sellerDescription;
	
	private String sellerOfficeAddress;
	
	private String ratings;
	
	private boolean sellerVerificationStatus = true;
	
	private String sellerTermsConditions;
	
}
