package com.i2i.spring.service;

import com.i2i.spring.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String emailId);

    User addUser(User user);

    List<User> getAllUser();
}
