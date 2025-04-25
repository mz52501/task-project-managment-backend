package com.example.taskandprojectmanagment.controller;

import com.example.taskandprojectmanagment.dto.ErrorRes;
import com.example.taskandprojectmanagment.dto.LoginReq;
import com.example.taskandprojectmanagment.dto.LoginRes;
import com.example.taskandprojectmanagment.dto.RegisterReq;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.repository.UserRepository;
import com.example.taskandprojectmanagment.security.JwtUtil;
import com.example.taskandprojectmanagment.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            User user  =((UserDetailsImpl) authentication.getPrincipal()).getUser();
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(authentication.getName(), token);
            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody RegisterReq registerReq) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerReq.getPassword());
        User user = new User(registerReq.getFirstName(), registerReq.getLastName(), registerReq.getEmail(), encodedPassword, registerReq.getImage(), registerReq.getRole());
        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
