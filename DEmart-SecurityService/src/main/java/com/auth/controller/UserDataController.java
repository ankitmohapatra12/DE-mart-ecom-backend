package com.auth.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Constants.PortalConstants;
import com.auth.models.Role;
import com.auth.models.UserAddresses;
import com.auth.models.UserCreds;
import com.auth.models.UserPresonal;
import com.auth.models.UserRole;
import com.auth.service.UserCredService;
import com.auth.service.UserPersonalService;
import com.auth.service.ServiceImpl.CustomerUserDetailsService;
import com.auth.service.ServiceImpl.UserCredServiceImpl;

@RestController
@RequestMapping("/profile")
public class UserDataController {
	
	@Autowired
	private UserCredService userService;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	private UserCredServiceImpl userServiceImpl;
	

	@Autowired
	private UserPersonalService userPersonalService;
	
	
	
	
	@PostMapping("/address/add-address")
	public UserAddresses saveAddress(@RequestBody UserAddresses address) throws Exception {
		System.out.println(address);
		UserCreds userCreds = new UserCreds();
		try {
			userCreds=userService.findByUserId(address.getUserId());
		}catch (Exception e) {
			throw new UsernameNotFoundException("User not found !!");		
		}
		
		address.setUserAddresses(userCreds);
		address = userPersonalService.saveUserAddress(address);
		return address;
	}
	
	
	@GetMapping("/address/get-addresses/{userId}")
	public ResponseEntity<List<UserAddresses>> getAddresses(@PathVariable String userId) throws Exception {
		return ResponseEntity.ok(userPersonalService.getAddresses(Long.parseLong(userId)));
	}
	
	
	@DeleteMapping("/address/delete-address/{userAddressId}")
	public ResponseEntity<UserAddresses> deleteAddress(@PathVariable String userAddressId) throws Exception {
		System.out.println(userAddressId);
		userPersonalService.deleteAddress(Long.parseLong(userAddressId));
		return ResponseEntity.ok(new UserAddresses());
	}
	
	@PostMapping("/personal/add-details")
	public UserPresonal createUser(@RequestBody UserPresonal userPersonal) throws Exception {
		UserCreds userCreds = new UserCreds();
		try {
			userCreds=userService.findByUserId(userPersonal.getUserId());
		}catch (Exception e) {
			throw new UsernameNotFoundException("User not found !!");		
		}
		boolean flag = false;
		
		if(!userCreds.getUserEmail().equals(userPersonal.getUserEmail())) {
			userCreds.setUserEmail(userPersonal.getUserEmail());
			flag = true;
		}
		if(!userCreds.getMobile().equals(userPersonal.getMobile())) {
			userCreds.setMobile(userPersonal.getMobile());
			flag =true;
		}
		
		try {
			if(flag) {
				userService.updateUser(userCreds);
			}
			
		}catch (Exception e) {
			throw new Exception("Unable to save email/password !!");
		}
		
		UserPresonal userData = new UserPresonal();
		try {
			userData = userPersonalService.findByUserData(userCreds);
		}catch (Exception e) {
			throw new Exception("Unable to fetch details");
		}
		
		
		if(userData!=null) {
			if(!userPersonal.getBirthDay().equalsIgnoreCase(userData.getBirthDay()) || 
					!userPersonal.getGender().equalsIgnoreCase(userData.getGender()) ||
					!(userPersonal.getPincode()!=userData.getPincode()) ||
					!userPersonal.getUserFullName().equalsIgnoreCase(userData.getUserFullName())) {
				try {
					if(userData.getUserPersonalDataId()!=0) {
						userPersonal.setUserPersonalDataId(userData.getUserPersonalDataId());
					}
					
					userPersonal.setUserData(userCreds);
					userPersonal = userPersonalService.saveUserPersonalData(userPersonal);
				}catch (Exception e) {
					throw new Exception("Unable to save user personal data !!");
				}
			}
		}
		else {
			userPersonal.setUserData(userCreds);
			userPersonal = userPersonalService.saveUserPersonalData(userPersonal);
		}
		System.out.println(userPersonal);
		return userPersonal;
	}
	
	

	@GetMapping("/personal/get-details/{userId}")
	public UserPresonal getUserDetails(@PathVariable String userId) throws Exception {
		UserCreds userCreds = new UserCreds();
		System.out.println(userId);
		try {
			userCreds=userService.findByUserId(Long.parseLong(userId));
		}catch (Exception e) {
			throw new UsernameNotFoundException("User not found !!");		
		}
		UserPresonal userPresonal = userPersonalService.findByUserData(userCreds);
		System.out.println(userPresonal);
		return userPresonal;
	}
	
	
}
