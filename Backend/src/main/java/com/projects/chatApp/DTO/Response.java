package com.projects.chatApp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private int httpCode;
    private MessageDTO messageDTO;
    private List<MessageDTO> messageDTOS;
    private String JwtToken;
    private String username;
}
