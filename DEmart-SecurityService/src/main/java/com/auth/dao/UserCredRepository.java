package com.auth.dao;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.models.UserCreds;



@Repository
public interface UserCredRepository extends JpaRepository<UserCreds, Long>{

	

	UserCreds findByUserName(String userName);

	
	

}
