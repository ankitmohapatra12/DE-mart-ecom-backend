package com.auth.service;

import org.springframework.stereotype.Service;

import com.auth.models.Role;



@Service
public interface RoleService {

	public Role getRoles(String roleName);
}
