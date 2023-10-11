package com.auth.service.ServiceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dao.RoleRepository;
import com.auth.dao.UserCredRepository;
import com.auth.models.UserCreds;
import com.auth.models.UserRole;
import com.auth.service.UserCredService;



@Service
public class UserCredServiceImpl implements UserCredService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserCredRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public UserCreds createUser(UserCreds user,Set<UserRole> userRoles) throws Exception {
		
		UserCreds checkUser = this.userRepo.findByUserName(user.getUserName());
		if(checkUser!=null) {
			throw new Exception("User is already present !!");
		}
		else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println(userRoles);
			System.out.println(user);
			//user.setUserRoles(userRoles);
			for(UserRole ur:userRoles) {
				roleRepo.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			System.out.println(user.toString());
			checkUser = this.userRepo.save(user);
			
		}
		
		
		return userRepo.save(user);
	}
	
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	
	public void validateToken(String token) {
		 jwtService.validateToken(token);
	}

}
