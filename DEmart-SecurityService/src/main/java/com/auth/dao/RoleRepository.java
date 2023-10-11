package com.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.models.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(String roleName);

}
