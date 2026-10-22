package com.fullstack.service;

import com.fullstack.entity.User;
import com.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public User signUp(User user) {

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserEmailId(username);


        return new org.springframework.security.core.userdetails.User(user.getUserEmailId(), user.getUserPassword(), new ArrayList<>());
    }
}
