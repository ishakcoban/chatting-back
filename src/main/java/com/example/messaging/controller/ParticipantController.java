package com.example.messaging.controller;

import com.example.messaging.core.SuccessMessage;
import com.example.messaging.modal.Participant;
import com.example.messaging.service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/participant")
@CrossOrigin
@AllArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;
    @PostMapping("/getAll")
    public Object getMessages(HttpServletRequest request, @RequestBody Participant participant) throws Exception {
        return ResponseEntity.ok(participantService.getMessages(participant));
    }

    @PostMapping("/send/{id}")
    public ResponseEntity<?> sendMessage(HttpServletRequest request, @RequestBody Participant participant, @PathVariable("id") String id) throws Exception {

        participantService.sendMessage(participant,id);
        return ResponseEntity.ok(new SuccessMessage("Sent",request.getServletPath(),""));
    }

    @PostMapping("/getAllChatList/{id}")
    public ResponseEntity<?> getAllChatList(HttpServletRequest request, @PathVariable("id") String id) throws Exception {

        return ResponseEntity.ok(participantService.getAllChatList(id));
    }
}
