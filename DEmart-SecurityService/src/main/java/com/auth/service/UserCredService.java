package com.auth.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.auth.models.UserCreds;
import com.auth.models.UserRole;

@Service
public interface UserCredService {

	UserCreds createUser(UserCreds user,Set<UserRole> roles) throws Exception;
}
