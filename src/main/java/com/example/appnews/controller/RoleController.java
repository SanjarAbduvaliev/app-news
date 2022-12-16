package com.example.appnews.controller;

import com.example.appnews.entity.Role;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.RoleDto;
import com.example.appnews.serivice.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @PostMapping("/addrole")
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_ROLE')")
    @GetMapping("/getByName")
    public Role getName(@RequestBody RoleDto roleDto){
        return roleService.getName(roleDto);
    }
    @PreAuthorize(value = "hasAnyAuthority('VIEW_ROLE')")
    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Role> allRole = roleService.getAllRole();
        return ResponseEntity.ok(allRole);
    }
    @PreAuthorize(value = "hasAnyAuthority('EDIT_ROLE')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editRole(@RequestBody RoleDto roleDto,@PathVariable Long id){
        ApiResponse apiResponse = roleService.editRole(roleDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAnyAuthority('DELETE_ROLE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        ApiResponse apiResponse = roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
