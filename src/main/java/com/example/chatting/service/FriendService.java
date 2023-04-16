package com.example.chatting.service;

import com.example.chatting.entity.Friend;
import com.example.chatting.entity.User;
import com.example.chatting.repository.FriendRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FriendService {

    FriendRepository friendDao;
    public Friend save(Friend friend) throws Exception {
        return friendDao.save(friend);
    }

    public Friend findById(String id) throws Exception {
        return friendDao.findById(id).orElseThrow();
    }
    public List<User> getAllUsers(String id) throws Exception {
        return friendDao.getNonFriends(new ObjectId(id));
    }
    public List<User> getAllFriends(String id) throws Exception {
        return friendDao.getAllFriends(new ObjectId(id));
    }

    public void addFriend(String userId,String friendId) throws Exception {

        Friend updated1 = friendDao.addFriend(new ObjectId(userId));
        Friend updated2 = friendDao.addFriend(new ObjectId(friendId));



        if(updated1 == null ){
            ArrayList<ObjectId> friends = new ArrayList<>();
            friends.add(new ObjectId(friendId));
            Friend f1 = new Friend();
            f1.setUserId(new ObjectId(userId));
            f1.setFriends(friends);
            friendDao.save(f1);

        }else{

            updated1.getFriends().add(new ObjectId(friendId));
            friendDao.save(updated1);

        }

        if(updated2 == null ){
            ArrayList<ObjectId> friends = new ArrayList<>();
            friends.add(new ObjectId(userId));
            Friend f1 = new Friend();
            f1.setUserId(new ObjectId(friendId));
            f1.setFriends(friends);
            friendDao.save(f1);

        }else{
            updated2.getFriends().add(new ObjectId(userId));
            friendDao.save(updated2);
        }


    }
}
