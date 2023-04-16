package com.example.chatting.controller;

import com.example.chatting.entity.Friend;
import com.example.chatting.entity.User;
import com.example.chatting.exception.SuccessMessage;
import com.example.chatting.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(HttpServletRequest request, @RequestBody User user) throws Exception {

        userService.save(user);
        return ResponseEntity.ok(new SuccessMessage("added",request.getServletPath(),""));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.findByEmail(user.getEmail()));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request, @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.getAllUsers(user.getId()));
    }

    @PostMapping("/getAllFriends/{id}")
    public ResponseEntity<List<Object>> getAllFriends(HttpServletRequest request, @PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(userService.getAllFriends(id));
    }

    @PostMapping("/friend/add/{id}")
    public ResponseEntity<?> addFriend(HttpServletRequest request,@PathVariable("id") String id, @RequestBody Friend friend) throws Exception {
        userService.addFriend(id,friend);
        return ResponseEntity.ok(new SuccessMessage("added",request.getServletPath(),""));
    }

    @PostMapping("/sendRequest/{id}")
    public ResponseEntity<?> sendRequest(HttpServletRequest request,@PathVariable("id") String id, @RequestBody User user) throws Exception {
        userService.sendRequest(id, user.getId());
        return ResponseEntity.ok(new SuccessMessage("sent request",request.getServletPath(),""));
    }

    @PutMapping("/deleteRequest/{id}")
    public ResponseEntity<?> deleteRequest(HttpServletRequest request,@PathVariable("id") String id, @RequestBody User user) throws Exception {
        userService.deleteRequest(id, user.getId());
        return ResponseEntity.ok(new SuccessMessage("request deleted",request.getServletPath(),""));
    }


}
