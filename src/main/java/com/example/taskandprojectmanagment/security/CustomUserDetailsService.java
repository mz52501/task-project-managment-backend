package com.example.taskandprojectmanagment.security;

import com.example.taskandprojectmanagment.model.User;
import com.example.taskandprojectmanagment.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new UserDetailsImpl(user);
    }
}
