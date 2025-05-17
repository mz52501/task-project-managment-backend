package com.example.taskandprojectmanagment.controller;

import com.example.taskandprojectmanagment.dto.ErrorRes;
import com.example.taskandprojectmanagment.dto.LoginReq;
import com.example.taskandprojectmanagment.dto.LoginRes;
import com.example.taskandprojectmanagment.dto.RegisterReq;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.repository.UserRepository;
import com.example.taskandprojectmanagment.security.JwtUtil;
import com.example.taskandprojectmanagment.security.UserDetailsImpl;
import com.example.taskandprojectmanagment.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Controller
@RequestMapping
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RegistrationService registrationService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, RegistrationService registrationService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.registrationService = registrationService;
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

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> register(
        @RequestPart("user") String userJson,
        @RequestPart("image") MultipartFile image) throws IOException {

        return registrationService.createUserWithImage(userJson, image);
    }
}
