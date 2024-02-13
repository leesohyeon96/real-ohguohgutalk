package com.shl.ohguohgutalk.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_id")
    @NotEmpty(message = "아이디는 필수입니다.")
    private String userId; // 중복되면 안됨
    @Column(name = "user_nick")
    @NotEmpty(message = "닉네임은 필수입니다.")
    private String userNick; // 중복되도 됨
    @Column(name = "user_password")
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;
    @Column(name = "user_email")
    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;
}
