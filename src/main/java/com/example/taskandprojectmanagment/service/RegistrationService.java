package com.example.taskandprojectmanagment.service;

import com.example.taskandprojectmanagment.dto.RegisterReq;
import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class RegistrationService {

    private final String reactPublicPath = "C:\\master_degree_study\\master_thesis\\backend\\task-and-project-managment\\src\\main\\resources\\static\\images\\";
    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(RegisterReq dto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(), encodedPassword, dto.getRole());

        return userRepository.save(user);
    }

    public ResponseEntity<User> createUserWithImage(String userJson, MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        RegisterReq registerReq = objectMapper.readValue(userJson, RegisterReq.class);

        User savedUser = createUser(registerReq);

        String imageFileName = savedUser.getId() + ".jpg";
        File imageFile = new File(reactPublicPath + imageFileName);
        try (FileOutputStream outputStream = new FileOutputStream(imageFile)) {
            outputStream.write(image.getBytes());
        }

        User updatedUser = userRepository.findById(savedUser.getId()).orElseThrow(
            () -> new RuntimeException("Employee not found after save"));

        // Update image path and save again
        updatedUser.setImage(imageFileName);
        userRepository.save(updatedUser);

        return ResponseEntity.ok(savedUser);
    }
}
