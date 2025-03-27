package com.kitcha.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpDto {
    @NotBlank
    private String nickname;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
