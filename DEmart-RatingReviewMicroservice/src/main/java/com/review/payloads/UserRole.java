package com.review.payloads;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole{


	private Long userRoleId;
	
	
	private UserCreds user;
	
	
	private Role role;

	
	
	
}

