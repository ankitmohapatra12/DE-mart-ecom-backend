package com.auth.service.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.dao.UserCredRepository;
import com.auth.models.CustomUserDetails;
import com.auth.models.UserCreds;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserCredRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCreds> credential = Optional.ofNullable(userRepo.findByUserName(username));
		System.out.println(credential.get().getAuthorities());
		return credential.get();
	}

}
