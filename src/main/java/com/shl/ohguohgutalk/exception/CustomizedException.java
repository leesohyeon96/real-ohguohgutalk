package com.shl.ohguohgutalk.exception;

import com.shl.ohguohgutalk.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class CustomizedException extends RuntimeException {
    private String errorMessage;
    private Member member;
}
