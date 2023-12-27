package com.shl.ohguohgutalk.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @NotEmpty
    private String id;
    @NotEmpty
    private String usernick;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
}
