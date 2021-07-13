package com.ramesh.shopping.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountInfomation {
	private String userName;
	private String email;
	private GenderType genderType; 
	
	public AccountInfomation() {}

	public AccountInfomation(String userName, String email, GenderType genderType) {
		super();
		this.userName = userName;
		this.email = email;
		this.genderType = genderType;
	}
	
}
