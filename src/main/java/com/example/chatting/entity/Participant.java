package com.example.chatting.entity;

import com.example.chatting.modal.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Participant {

    @Id
    private String id;
    private List<Object> participants;
    private List<MessageResponse> messages;
    private LocalDateTime lastMessageDate;

}
