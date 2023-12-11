package com.review.payloads;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddresses {

	

	private long userAddressId;
	
	private String name;
	
	private String mobile;
	
	private String pincode;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String locality;
	
	private String district;
	
	private String addressType;
	
	
	private String address;
	
	private boolean defaultAddressUser;
	
	
	
	private long userId;
	
	

	private UserCreds userAddresses;
}
