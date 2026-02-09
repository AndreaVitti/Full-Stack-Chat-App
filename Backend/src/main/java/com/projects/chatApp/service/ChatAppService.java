package com.projects.chatApp.service;

import com.projects.chatApp.DTO.MessageDTO;
import com.projects.chatApp.DTO.Response;

public interface ChatAppService {
    Response mapperMsgType(MessageDTO messageDTO);

    Response getAllMsgs();
}
