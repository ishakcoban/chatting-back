package com.example.chatting.service;

import com.example.chatting.entity.Friend;
import com.example.chatting.entity.User;
import com.example.chatting.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    PasswordEncoder encoder;
    public User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow(() -> new Exception("UserNotFoundException"));
    }
    public User findById(String uid) throws Exception {

        return userRepository.findById(uid).orElseThrow(() -> new Exception("UserNotFoundException"));
    }

    public void checkEmailExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The email already taken!");
        }
    }
    public User save(User user) throws Exception {
        User userDetails = null;
        try {
            userDetails = findByEmail(user.getEmail());
        } catch (Exception ignored) {
        }
        if (userDetails != null)
            throw new Exception("ExistingUserException");
       /* user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
        user.setVerified(true);*/

        User updated = user;
        updated.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(updated);
    }

    public List<User> getAllUsers(String id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").ne(new ObjectId(id)));
        return mongoOperations.find(query,User.class);

    }

    public List<Object> getAllFriends(String id) throws Exception {
       /* Query query = new Query();
        query.addCriteria(Criteria.where("id").is("62a495e1e0d6df4c7e17136d")).fields();*/
        //query.addCriteria(Criteria.where("friends").is(Criteria.where("id")));

        return userRepository.findById(id).orElseThrow().getFriends();

    }
    public User update(User user) throws Exception {
        return userRepository.save(user);
    }

    public User addFriend(String id, Friend friend){

        User updated = userRepository.findById(id).orElseThrow();

        //updated.getFriends().add(friend);

        return userRepository.save(updated);
    }

    public User sendRequest(String id,String friendId){

        User updated = userRepository.findById(id).orElseThrow();

        updated.getRequests().add(friendId);

        return userRepository.save(updated);
    }

    public User deleteRequest(String id,String friendId){

        User updated = userRepository.findById(id).orElseThrow();
        updated.getRequests().remove(friendId);

        return userRepository.save(updated);
    }

}
