package com.auth;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth.Constants.PortalConstants;
import com.auth.models.Role;
import com.auth.models.UserCreds;
import com.auth.models.UserRole;
import com.auth.service.RoleService;
import com.auth.service.UserCredService;

@SpringBootApplication
@EnableDiscoveryClient
public class DEmartSecurityServiceApplication implements CommandLineRunner{
	
	@Autowired
	private UserCredService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(DEmartSecurityServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		UserCreds user = new UserCreds();
//		user.setUserName("admin");
//		user.setPassword("admin");
//		user.setUserEmail("ankitmohapatra@gmail.com");
//		
//		user.setMobile("9337053395");
//		
//		Role role = new Role();
//		role = roleService.getRoles(String.valueOf(PortalConstants.ADMIN));
//		
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		
//		UserRole ur = new UserRole();
//		ur.setUser(user);
//		ur.setRole(role);
//		//System.out.println(ur);
//		//user.getUserRoles().add(ur);
//		userRoleSet.add(ur);
//		//user.setUserRoles(userRoleSet);
//		
//		
//		UserCreds admin = this.userService.createUser(user,userRoleSet);
//		System.out.println(admin.getUsername());
	}
	
	

}
