package com.projects.chatApp.DTO;
import lombok.Data;

@Data
public class MessageDTO {
    private String type;
    private String sender;
    private String payload;
}
