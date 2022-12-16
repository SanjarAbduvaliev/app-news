package com.example.appnews.entity;

import com.example.appnews.entity.enums.Permission;
import com.example.appnews.entity.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

//lavozim
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {

    @Column(unique = true,nullable = false)
    private String name;


    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;

    @Column(columnDefinition = "text")
    private String description;
}
