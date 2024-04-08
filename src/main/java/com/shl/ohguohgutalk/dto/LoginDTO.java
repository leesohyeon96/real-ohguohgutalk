package com.shl.ohguohgutalk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 DTO
 */
@Data
@Setter
@Getter
public class LoginDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
