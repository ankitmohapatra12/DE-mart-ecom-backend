package com.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerData {

	private long userId;
	private String username;
	private String email;
	private String phone;
	private String password;
}
