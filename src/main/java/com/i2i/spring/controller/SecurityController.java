package com.i2i.spring.controller;

import com.i2i.spring.model.AuthenticationResponse;
import com.i2i.spring.model.AuthenticationRequest;
import com.i2i.spring.service.UserService;
import com.i2i.spring.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil tokenUtil;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    public PasswordEncoder passwordEncoder ;

    @RequestMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmailId(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Incorrect email-id or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmailId());
        final String webToken = tokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(webToken));
    }
}
