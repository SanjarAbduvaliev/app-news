package com.example.appnews.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Username kiriting")
    private String username;

    @NotBlank(message = "Parolni kiritng")
    private String password;
}
