package com.shl.ohguohgutalk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class EmptyParameterException extends RuntimeException {
    private String errorMessage;
    private Object pathVairable;
    private String priorUrl;
}
