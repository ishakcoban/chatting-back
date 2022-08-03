package com.example.messaging.service;

import com.example.messaging.modal.Friend;
import com.example.messaging.modal.User;
import com.example.messaging.repository.UserDao;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    UserDao userDao;
    @Autowired
    private MongoOperations mongoOperations;
    public User findByEmail(String email) throws Exception {
        return userDao.findByEmail(email).orElseThrow(() -> new Exception("UserNotFoundException"));
    }
    public User findById(String uid) throws Exception {

        return userDao.findById(uid).orElseThrow(() -> new Exception("UserNotFoundException"));
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

        return userDao.save(user);
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

        return userDao.findById(id).orElseThrow().getFriends();

    }
    public User update(User user) throws Exception {
        return userDao.save(user);
    }

    public User addFriend(String id,Friend friend){

        User updated = userDao.findById(id).orElseThrow();

        //updated.getFriends().add(friend);

        return userDao.save(updated);
    }

    public User sendRequest(String id,String friendId){

        User updated = userDao.findById(id).orElseThrow();

        updated.getRequests().add(friendId);

        return userDao.save(updated);
    }

    public User deleteRequest(String id,String friendId){

        User updated = userDao.findById(id).orElseThrow();
        updated.getRequests().remove(friendId);

        return userDao.save(updated);
    }

}
