package com.example.messaging.controller;

import com.example.messaging.modal.response.WebSocketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public WebSocketResponse receiveMessage(@Payload WebSocketResponse message){

        return message;
    }

    @MessageMapping("/private-message")
    public WebSocketResponse recMessage(@Payload WebSocketResponse message){
        simpMessagingTemplate.convertAndSendToUser(message.getSenderId(),"/private",message);

        return message;
    }
}
