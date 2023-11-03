package com.auth.service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dao.AddressRepository;
import com.auth.dao.RoleRepository;
import com.auth.dao.UserCredRepository;
import com.auth.dao.UserPersonalRepository;
import com.auth.models.UserAddresses;
import com.auth.models.UserCreds;
import com.auth.models.UserPresonal;
import com.auth.models.UserRole;
import com.auth.service.UserCredService;
import com.auth.service.UserPersonalService;

import jakarta.transaction.Transactional;



@Service
public class UserCredServiceImpl implements UserCredService,UserPersonalService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserPersonalRepository userPersonalRepo;
	
	@Autowired
	private UserCredRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AddressRepository addressRepository;

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


	@Override
	public UserCreds findByUserId(long userId) {
		// TODO Auto-generated method stub
		return userRepo.findByUserId(userId);
	}


	@Override
	public UserCreds updateUser(UserCreds userCreds) {
		// TODO Auto-generated method stub
		return userRepo.saveAndFlush(userCreds);
	}


	@Override
	public UserPresonal saveUserPersonalData(UserPresonal userPersonal) {
		// TODO Auto-generated method stub
		return userPersonalRepo.saveAndFlush(userPersonal);
	}


	@Override
	public UserPresonal findByUserData(UserCreds userCreds) {
		// TODO Auto-generated method stub
		return userPersonalRepo.findByUserData(userCreds);
	}


	@Override
	public UserAddresses saveUserAddress(UserAddresses address) {
		// TODO Auto-generated method stub
		return addressRepository.saveAndFlush(address);
	}


	@Override
	public List<UserAddresses> getAddresses(long userId) throws Exception {
		// TODO Auto-generated method stub
		List<UserAddresses> addresses = new ArrayList<>();
		try {
			addresses = addressRepository.findByUserAddresses(userId);
		}catch (Exception e) {
			throw new Exception("Unable to fetch address !!");
		}
		return addresses;
	}


	@Transactional
	@Override
	public void deleteAddress(long userAddressId) {
		// TODO Auto-generated method stub
		System.out.println(userAddressId);
		addressRepository.deleteByUserAddressId(userAddressId);
	}

	
	
}
