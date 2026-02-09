package com.projects.chatApp.service;

import com.projects.chatApp.DTO.LoginReq;
import com.projects.chatApp.DTO.RegisterReq;
import com.projects.chatApp.DTO.Response;

public interface UserService {
    Response register(RegisterReq registerReq);

    Response login(LoginReq registerReq);
}
