package com.auth.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auth.models.UserAddresses;
import com.auth.models.UserCreds;
import com.auth.models.UserPresonal;

@Service
public interface UserPersonalService {

	UserPresonal saveUserPersonalData(UserPresonal userPersonal);

	UserPresonal findByUserData(UserCreds userCreds);

	UserAddresses saveUserAddress(UserAddresses address);

	List<UserAddresses> getAddresses(long userId) throws Exception;

	void deleteAddress(long userAddressId);

}
