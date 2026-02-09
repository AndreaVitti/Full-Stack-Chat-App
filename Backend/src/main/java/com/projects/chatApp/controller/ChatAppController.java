package com.projects.chatApp.controller;

import com.projects.chatApp.DTO.Response;
import com.projects.chatApp.service.ChatAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatApp")
public class ChatAppController {

    private final ChatAppService chatAppService;

    @GetMapping("/message/all")
    public ResponseEntity<Response> getAllMsgs(){
        Response response = chatAppService.getAllMsgs();
        return ResponseEntity.status(response.getHttpCode()).body(response);
    }
}
