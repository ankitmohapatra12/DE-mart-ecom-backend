package com.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.models.UserCreds;
import com.auth.models.UserPresonal;


@Repository
public interface UserPersonalRepository extends JpaRepository<UserPresonal, Long> {

	UserPresonal findByUserData(UserCreds userCreds);

}
