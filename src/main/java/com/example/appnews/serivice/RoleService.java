package com.example.appnews.serivice;

import com.example.appnews.entity.Role;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.RoleDto;
import com.example.appnews.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public ApiResponse addRole(RoleDto roleDto){
        boolean exists = roleRepository.existsByName(roleDto.getName());
        if (exists){
            return new ApiResponse("Bunday lavozim mavjud",false);
        }
        Role role=new Role(roleDto.getName(),
                roleDto.getPermissionList(),
                roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("Lavozim qo'shildi",true);
    }
    public ApiResponse editRole(RoleDto roleDto,Long id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty()){
            return new ApiResponse("Bunday lavozim mavjud emas",false);
        }
        Role role = optionalRole.get();
        role.setDescription(roleDto.getDescription());
        role.setPermissionList(roleDto.getPermissionList());
        role.setName(roleDto.getName());
        roleRepository.save(role);
        return new ApiResponse("Lavozim tahrirlandi",true);
    }
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
    public Role getName(RoleDto roleDto){
        Optional<Role> optionalRole = roleRepository.findByName(roleDto.getName());
        return optionalRole.orElse(null);
    }
    public ApiResponse deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
        }
        catch (Exception e) {
            return null;
        }
        return null;
    }
}
