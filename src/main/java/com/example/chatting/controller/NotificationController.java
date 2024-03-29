package com.example.chatting.controller;

import com.example.chatting.entity.User;
import com.example.chatting.exception.SuccessMessage;
import com.example.chatting.service.FriendService;
import com.example.chatting.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/notification")
@CrossOrigin
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final FriendService friendService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getNotifications(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(notificationService.getNotifications(id));
    }

    @PostMapping("/sendRequest/{id}")
    public ResponseEntity<?> sendRequest(HttpServletRequest request,@PathVariable("id") String id,@RequestBody User user) throws Exception {
        notificationService.sendRequest(id, user.getId());
        return ResponseEntity.ok(new SuccessMessage("sent requested", request.getServletPath(), ""));
    }

    @PostMapping("/buttonStatus/{id}")
    public ResponseEntity<?> determineButtonStatus(HttpServletRequest request,@PathVariable("id") String id,@RequestBody User user) throws Exception {
        return ResponseEntity.ok(notificationService.analyseButtonStatus(id, user.getId()));
    }

    @PutMapping("/deleteRequest/{id}")
    public ResponseEntity<?> deleteRequest(HttpServletRequest request,@PathVariable("id") String id,@RequestBody User user) throws Exception {
        notificationService.deleteRequest(id, user.getId());
        return ResponseEntity.ok(new SuccessMessage("deleted", request.getServletPath(), ""));
    }

    @PostMapping("/acceptRequest/{id}")
    public ResponseEntity<?> acceptRequest(HttpServletRequest request,@PathVariable("id") String id,@RequestBody User user) throws Exception {
        notificationService.acceptRequest(id, user.getId());
        friendService.addFriend(id, user.getId());
        return ResponseEntity.ok(new SuccessMessage("request accepted", request.getServletPath(), ""));
    }

    @GetMapping("/getAllNotifications/{id}")
    public ResponseEntity<?> concat(HttpServletRequest request,@PathVariable("id") String id) throws Exception {

        return ResponseEntity.ok(notificationService.concatNotification(id));
    }

    @GetMapping("/setTrueAllSeenStatus/{id}")
    public ResponseEntity<?> setTrueAllSeenStatus(HttpServletRequest request,@PathVariable("id") String id) throws Exception {
        notificationService.setTrueAllSeenStatus(id);
        return ResponseEntity.ok(new SuccessMessage("set all true", request.getServletPath(), ""));
    }
}
