package com.example.messaging.modal.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageResponse {

    private String sender;
    private String content;
    private LocalDateTime dateTime;

}
