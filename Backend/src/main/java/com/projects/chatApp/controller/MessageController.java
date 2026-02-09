package com.projects.chatApp.controller;

import com.projects.chatApp.DTO.MessageDTO;
import com.projects.chatApp.DTO.Response;
import com.projects.chatApp.service.ChatAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final ChatAppService chatAppService;

    @MessageMapping("/sendMessage")
    @SendTo("/chat/public")
    public MessageDTO sendMessage(@Payload MessageDTO messageDTO) {
        Response response = chatAppService.mapperMsgType(messageDTO);
        return response.getMessageDTO();
    }
}
