package com.products.dto;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
