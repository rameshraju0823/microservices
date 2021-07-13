package com.ramesh.payment.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CredentialDto {
	private String email;
	private String password;

}
