package com.example.appnews.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotNull(message = "Parolni kiriting")
    private String username;

    @NotNull(message = "Parolni kiriting")
    private String password;

    private Integer roleId;


}
