package com.ramesh.shopping.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorException {

    private int errorCode;
    private String errorMessage;
}
