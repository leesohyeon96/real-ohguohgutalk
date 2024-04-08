package com.shl.ohguohgutalk.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 로그인 Domain
 */
@Data
@Setter
@Getter
public class LoginMember {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
