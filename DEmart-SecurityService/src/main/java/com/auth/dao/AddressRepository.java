package com.auth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.models.UserAddresses;
import com.auth.models.UserCreds;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface AddressRepository extends JpaRepository<UserAddresses, Long> {

	
	@Query(value="SELECT * FROM user_addresses WHERE user_addresses_user_id = :userId",nativeQuery = true)
	List<UserAddresses> findByUserAddresses(long userId);

	@Modifying
	@Query(value="DELETE FROM user_addresses ua WHERE ua.user_address_id = :userAddressId",nativeQuery = true)
	void deleteByUserAddressId(long userAddressId);

	

}
