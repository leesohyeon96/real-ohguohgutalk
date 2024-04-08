package com.shl.ohguohgutalk.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberDTO {
    private long id;
    @NotEmpty(message = "아이디는 필수입니다.")
    private String userId;
    @NotEmpty(message = "닉네임은 필수입니다.")
    private String userNick;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;
    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;
    private String salt;
}
