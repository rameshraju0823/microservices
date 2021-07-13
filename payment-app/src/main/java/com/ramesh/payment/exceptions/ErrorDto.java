package com.ramesh.payment.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
	
	private int errorCode;
	private String errorMessage;

}