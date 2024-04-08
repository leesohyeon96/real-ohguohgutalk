package com.shl.ohguohgutalk.entity;

import jakarta.persistence.*;
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
    private String userId; // 중복되면 안됨
    @Column(name = "user_nick")
    private String userNick; // 중복되도 됨
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "salt")
    private String salt;
}
