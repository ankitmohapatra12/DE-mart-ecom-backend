package com.auth.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Constants.PortalConstants;
import com.auth.dto.AuthRequest;
import com.auth.dto.SellerData;
import com.auth.dto.TokenResponse;
import com.auth.models.Role;
import com.auth.models.UserCreds;
import com.auth.models.UserRole;
import com.auth.service.RoleService;
import com.auth.service.UserCredService;
import com.auth.service.ServiceImpl.CustomerUserDetailsService;
import com.auth.service.ServiceImpl.UserCredServiceImpl;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserCredService userService;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	private UserCredServiceImpl userServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/register")
	public UserCreds createUser(@RequestBody UserCreds user) throws Exception {
		System.out.println(user);
		Set<UserRole> roles =  new HashSet<>();
		UserRole userRole = new UserRole();
		Role role = new Role();
		role = roleService.getRoles(String.valueOf(PortalConstants.USER));
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		UserCreds userCreds = userService.createUser(user,roles);
		return userCreds;
	}
	
	@PostMapping("/seller-register")
	public UserCreds createSellerAccount(@RequestBody SellerData seller) throws Exception {
		
		UserCreds user  = new UserCreds();
		user.setUserName(seller.getUsername());
		user.setPassword(seller.getPassword());
		user.setUserEmail(seller.getEmail());
		user.setMobile(seller.getPhone());
		
		System.out.println(user);
		Set<UserRole> roles =  new HashSet<>();
		UserRole userRole = new UserRole();
		Role role = new Role();
		role = roleService.getRoles(String.valueOf(PortalConstants.SELLER));
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		UserCreds userCreds = userService.createUser(user,roles);
		return userCreds;
	}
	
	
	@PostMapping("/token")
	public ResponseEntity<TokenResponse> getToken(@RequestBody AuthRequest request) {
		System.out.println(request);
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
		if(auth.isAuthenticated()) {
			String token = userServiceImpl.generateToken(request.getUserName());
			System.out.println(token);
			return ResponseEntity.ok(new TokenResponse(token));
		}
		else {
			
			return ResponseEntity.ok(new TokenResponse("Invalid Credentials"));
		}
		
	}
	
	@PostMapping("/current-user")
	public UserCreds getCurrentUser(@RequestBody UserCreds user) throws Exception {
		
		user =  (UserCreds)this.customerUserDetailsService.loadUserByUsername(user.getUsername());
		System.out.println(user.getUsername()+"           "+user.getMobile()+"  ");
		if(user==null) {
			throw new Exception("User not found !!");
		}
		return user;
	}
	
	
	
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		userServiceImpl.validateToken(token);
		return "Token is valid !";
	}

}
