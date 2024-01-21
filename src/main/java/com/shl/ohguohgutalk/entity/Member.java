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
    @NotEmpty
    private String userId;
    @Column(name = "user_nick")
    @NotEmpty
    private String userNick;
    @Column(name = "user_password")
    @NotEmpty
    private String password; // TODO: 암호화
    @Column(name = "user_email")
    @NotEmpty
    private String email;
}
