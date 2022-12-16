package com.example.appnews.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotNull(message = "Parolni kiriting")
    private String fullName;

    @NotNull(message = "Parolni kiriting")
    private String username;

    @NotNull(message = "Parolni kiriting")
    private String password;

    @NotNull(message = "Parolni kiriting")
    private String prePassword;
}
