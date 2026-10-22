package com.fullstack.controller;

import com.fullstack.dto.LogInRequest;
import com.fullstack.entity.User;
import com.fullstack.service.UserService;
import com.fullstack.util.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid User user) {

        return new ResponseEntity<>(userService.signUp(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> generateToken(@RequestBody LogInRequest logInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.userEmailId(), logInRequest.userPassword()));

        return new ResponseEntity<>(jwtUtil.generateToken(logInRequest.userEmailId()), HttpStatus.OK);
    }
}
