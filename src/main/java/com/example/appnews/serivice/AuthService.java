package com.example.appnews.serivice;

import com.example.appnews.config.JwtProvider;
import com.example.appnews.entity.User;
import com.example.appnews.entity.utils.AppConstant;
import com.example.appnews.exception.ResourceNotFoundException;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.RegisterDTO;
import com.example.appnews.repository.RoleRepository;
import com.example.appnews.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements UserDetailsService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    AuthService(@Lazy AuthenticationManager authenticationManager,
                @Lazy PasswordEncoder passwordEncoder,
                UserRepository userRepository,
                RoleRepository roleRepository,
                @Lazy JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtProvider = jwtProvider;
    }

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (!(registerDTO.getPassword().equals(registerDTO.getPrePassword()))) {
            return new ApiResponse("Parollar mos emas", false);
        }
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ApiResponse("Bunday username mavjud", false);
        }
        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleRepository.findByName(AppConstant.USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", AppConstant.USER)),
                true


        );
        userRepository.save(user);
        return new ApiResponse("Tabriklaymiz muvofaqqiyatlik ro'yhatdan o'tdingiz", true);
    }

//    public String login(LoginDto loginDto) {
//        Authentication authenticate = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
//                        loginDto.getPassword()));
//        User user = (User) authenticate.getPrincipal();
//
//        String token = jwtProvider.gerateToken(loginDto.getUsername(), user.getRole());
//        return token;
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " topilmadi"));
    }
}
