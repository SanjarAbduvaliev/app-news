package com.example.appnews.controller;

import com.example.appnews.config.JwtProvider;
import com.example.appnews.entity.User;
import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.LoginDto;
import com.example.appnews.payload.RegisterDTO;
import com.example.appnews.serivice.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;
    @Autowired
    LoginController(AuthService authService,
                    @Lazy AuthenticationManager authenticationManager){
        this.authService=authService;
        this.authenticationManager=authenticationManager;
    }
    @Autowired
    JwtProvider jwtProvider;
    @PreAuthorize(value = "hasAnyAuthority('ADD_USER')")//ADD_USER huquqi bor user kiraoladi
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO){
        ApiResponse apiResponse = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        User user = (User) authenticate.getPrincipal();

        String token = jwtProvider.gerateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok("Bearer "+token);

    }


}
