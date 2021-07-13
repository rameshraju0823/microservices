package com.ramesh.shopping.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Credentials {
	private String email;
	private String password;
	public Credentials() {}
	public Credentials(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	

}
