package com.ramesh.shopping.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Register {
	
	private String name;
	private String email;
	private String password;
	private GenderType gender;
	public Register() {}
	public Register(String name, String email, String password, GenderType gender) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}
	
	

}
