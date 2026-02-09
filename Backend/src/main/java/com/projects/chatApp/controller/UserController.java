package com.projects.chatApp.controller;

import com.projects.chatApp.DTO.LoginReq;
import com.projects.chatApp.DTO.Response;
import com.projects.chatApp.DTO.RegisterReq;
import com.projects.chatApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatApp")
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<Response> register(@RequestBody RegisterReq registerReq) {
        Response response = userService.register(registerReq);
        return ResponseEntity.status(response.getHttpCode()).body(response);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Response> login(@RequestBody LoginReq loginReq) {
        Response response = userService.login(loginReq);
        String jwtToken = response.getJwtToken();
        response.setJwtToken(null);
        return ResponseEntity.status(response.getHttpCode()).header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken).body(response);
    }
}
