package com.i2i.spring.service.impl;

import com.i2i.spring.repository.UserRepository;
import com.i2i.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) {

        com.i2i.spring.model.User user = userRepository.findByEmailId(emailId);

        return new User(user.getEmailId(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public com.i2i.spring.model.User addUser(com.i2i.spring.model.User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<com.i2i.spring.model.User> getAllUser() {

        return userRepository.findAll();

    }

}
