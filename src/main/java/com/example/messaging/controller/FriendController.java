package com.example.messaging.controller;

import com.example.messaging.core.SuccessMessage;
import com.example.messaging.modal.User;
import com.example.messaging.service.FriendService;
import com.example.messaging.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/friend")
@CrossOrigin
@AllArgsConstructor
public class FriendController {

    private final UserService userService;
    private final FriendService friendService;

    @PostMapping("/add/{userid}")
    public ResponseEntity<?> addFriend(HttpServletRequest request, @PathVariable("userid") String id, @RequestBody User user) throws Exception {

        friendService.addFriend(id,user.getId());

        return ResponseEntity.ok(new SuccessMessage("Friend added",request.getServletPath(),""));
    }

    @PostMapping("/getAllUsers")
    public ResponseEntity<?> getAllFriends(HttpServletRequest request, @RequestBody User user) throws Exception {

        return ResponseEntity.ok(friendService.getAllUsers(user.getId()));
    }

    @PostMapping("/getAllFriends")
    public ResponseEntity<?> getFriend(HttpServletRequest request, @RequestBody User user) throws Exception {
        return ResponseEntity.ok(friendService.getAllFriends(user.getId()));
    }
}
