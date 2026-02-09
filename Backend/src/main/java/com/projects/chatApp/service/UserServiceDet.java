package com.projects.chatApp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserServiceDet {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
