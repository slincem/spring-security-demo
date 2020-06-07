package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.JwtResponse;
import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.service.AccountUserDetailsService;
import com.example.springsecuritydemo.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AccountUserDetailsService accountUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authentication(@RequestBody User userToLogin) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userToLogin.getUsername(), userToLogin.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username and password", e);
        }

        final UserDetails accountUser = accountUserDetailsService.loadUserByUsername(userToLogin.getUsername());
        final String jwt = jwtUtil.generateToken(accountUser);

        return ResponseEntity.ok(new JwtResponse(jwt));

    }
}
