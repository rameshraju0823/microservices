package com.ramesh.payment.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BeneficiaryDto {
	private long accountId;
	private long beneficiaryId;

}
