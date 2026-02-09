package com.projects.chatApp.service.impl;

import com.projects.chatApp.DTO.LoginReq;
import com.projects.chatApp.DTO.RegisterReq;
import com.projects.chatApp.DTO.Response;
import com.projects.chatApp.entity.User;
import com.projects.chatApp.repository.UserRepository;
import com.projects.chatApp.service.UserService;
import com.projects.chatApp.utility.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Response register(RegisterReq registerReq) {
        Response response = new Response();
        User user = new User();
        user.setUsername(registerReq.getUsername());
        user.setEmail(registerReq.getEmail());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        userRepository.save(user);
        response.setHttpCode(200);
        return response;
    }

    @Override
    public Response login(LoginReq loginReq) {
        Response response = new Response();
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        loginReq.getEmail(),
                        loginReq.getPassword()
                )
        );
        final UserDetails username = (UserDetails) authentication.getPrincipal();
        assert username != null;
        response.setJwtToken(jwtUtil.generateToken(username.getUsername()));
        User user = userRepository.findByEmail(username.getUsername()).orElse(null);
        assert user != null;
        response.setUsername(user.getUsername());
        response.setHttpCode(200);
        return response;
    }
}
