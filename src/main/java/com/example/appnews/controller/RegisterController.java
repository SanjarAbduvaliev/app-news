package com.example.appnews.controller;

import com.example.appnews.payload.ApiResponse;
import com.example.appnews.payload.RegisterDTO;
import com.example.appnews.serivice.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    AuthService authService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO){
        ApiResponse apiResponse = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
