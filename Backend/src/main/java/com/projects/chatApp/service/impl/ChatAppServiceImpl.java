package com.projects.chatApp.service.impl;

import com.projects.chatApp.DTO.MessageDTO;
import com.projects.chatApp.DTO.Response;
import com.projects.chatApp.entity.Message;
import com.projects.chatApp.entity.MsgTypes;
import com.projects.chatApp.repository.ChatMsgRepository;
import com.projects.chatApp.repository.UserRepository;
import com.projects.chatApp.service.ChatAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatAppServiceImpl implements ChatAppService {

    private final UserRepository userRepository;
    private final ChatMsgRepository chatMsgRepository;

    @Override
    public Response mapperMsgType(MessageDTO messageDTO) {
        Response response = new Response();
        String username = messageDTO.getSender();
        switch (messageDTO.getType()) {
            case "CHAT":
                response.setMessageDTO(messageDTO);
                break;
            case "CONN":
                messageDTO.setPayload("The user " + username + " has connected to the chat.");
                response.setMessageDTO(messageDTO);
                break;
            case "DISC":
                messageDTO.setPayload("The user " + username + " has disconnected from the chat.");
                response.setMessageDTO(messageDTO);
                break;
        }
        Message message = new Message();
        message.setType(MsgTypes.valueOf(messageDTO.getType()));
        message.setPayload(messageDTO.getPayload());
        message.setSender(userRepository.findByUsername(username).orElse(null));
        chatMsgRepository.save(message);
        response.setHttpCode(200);
        return response;
    }

    @Override
    public Response getAllMsgs() {
        Response response = new Response();
        List<MessageDTO> messageDTOS = chatMsgRepository.findAll().stream().map(msg -> {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setSender(msg.getSender().getUsername());
            messageDTO.setType(msg.getType().name());
            messageDTO.setPayload(msg.getPayload());
            return messageDTO;
        }).toList();
        response.setMessageDTOS(messageDTOS);
        response.setHttpCode(200);
        return response;
    }
}
