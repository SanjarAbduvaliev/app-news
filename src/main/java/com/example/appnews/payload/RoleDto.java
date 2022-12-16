package com.example.appnews.payload;

import com.example.appnews.entity.Role;
import com.example.appnews.entity.enums.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    @NotBlank(message = "Lavozimini kiriting")
    private String name;

    private String description;

    @NotEmpty
    private List<Permission> permissionList;


}
