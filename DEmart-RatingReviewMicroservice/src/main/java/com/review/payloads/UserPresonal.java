package com.review.payloads;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPresonal {


	private long userPersonalDataId;
	private String userFullName;
	private String gender;
	private String birthDay;
	private long pincode;
	
	
	private String userEmail;
	
	
	private String mobile;
	

	private long userId;

	private UserCreds userData;
	
	
	
}
