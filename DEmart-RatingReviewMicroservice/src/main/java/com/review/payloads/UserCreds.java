package com.review.payloads;




import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreds{



	
	private long userId;
	private String userName;
	private String userEmail;
	private String mobile;
	private String password;
	
	private List<Authority> authorities =new ArrayList<>();

	
	private Set<UserRole> userRoles = new HashSet<>();

	
	

	private UserPresonal userPersonalData = new UserPresonal();
	
	
	private List<UserAddresses> userAddresses = new ArrayList<>();
	
	

	
	
}
