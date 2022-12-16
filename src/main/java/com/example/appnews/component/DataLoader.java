package com.example.appnews.component;

import com.example.appnews.entity.Role;
import com.example.appnews.entity.User;
import com.example.appnews.entity.enums.Permission;
import com.example.appnews.entity.utils.AppConstant;
import com.example.appnews.repository.RoleRepository;
import com.example.appnews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.appnews.entity.enums.Permission.*;

import java.util.Arrays;

//Majburiy userlar

@Component

public class DataLoader implements CommandLineRunner {

    private RoleRepository roleRepository;

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public DataLoader(RoleRepository roleRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Value("${spring.batch.jdbc.initialize-schema}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        Permission[] values = Permission.values();
        System.out.println("Ishladi");

        if (initialMode.equals("always")) {
            {
                Role admin = roleRepository.save(
                        new Role(AppConstant.ADMIN,
                                Arrays.asList(values),
                                "Asosiy admin"
                        ));
                Role user = roleRepository.save(new Role(
                                AppConstant.USER,
                                Arrays.asList(
                                        ADD_COMMENT,
                                        DELETE_MY_COMMENT,
                                        EDIT_COMMENT
                                ), "Asosiy user"
                        )
                );
                userRepository.save(new User(
                                "Admin",
                                "Admin",
                                passwordEncoder.encode("admin111"),
                                admin
                        )
                );
                userRepository.save(new User(
                                "User",
                                "User",
                                passwordEncoder.encode("user111"),
                                user

                        )
                );
            }
        }
    }
}
