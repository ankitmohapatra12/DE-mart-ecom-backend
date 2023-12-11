package com.review.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

	
	private long manufacturerId;
	
	private String manufacturerName;
	
	private String manufacturerEmail;
	
	private String manufacturerPhone;
	
	private String manufacturerAddress;
	
	private String manufacturerWebsite;
	
	private String manufacturerDescription;
	
	private String founderName;
	
	private long yearFounded;
	
	private String logoUrl;
	
	private String manufacturerEmployeeSize;
	
	

	private List<Item> manufacturingItems;
	
	private String rating;
	
	private boolean manufacturerVerificationStatus = true;
	
	private String manufacturerTermsConditions;
}
