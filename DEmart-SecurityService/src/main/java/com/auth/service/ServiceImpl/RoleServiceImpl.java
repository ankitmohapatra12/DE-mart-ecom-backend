package com.auth.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.dao.RoleRepository;
import com.auth.models.Role;
import com.auth.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getRoles(String roleName) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("USER");
		Role fetchedRole =  new Role();
		try {
			fetchedRole = roleRepository.findByRoleName(roleName);
		}
		catch (Exception e) {
			
		}
		return fetchedRole!=null?fetchedRole:role;
	}

}