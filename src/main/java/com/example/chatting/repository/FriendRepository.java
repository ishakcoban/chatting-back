package com.example.chatting.repository;

import com.example.chatting.entity.Friend;
import com.example.chatting.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FriendRepository extends MongoRepository<Friend,String> {

    @Aggregation(pipeline = {
            "{ $match:{ userId : ?0 } }",
            "{ $lookup: {from: 'user', let:{fid: '$friends'}, pipeline:[{ $match:{ $expr: { $not:{ $in:[ $_id,$$fid ]} , }} },], as:'result'}}",
            "{$unwind :{path: $result}}",
            "{$replaceRoot :{newRoot: $result}}",
            "{ $match:{ '_id': {'$ne': ?0}} }"
    })
    List<User> getNonFriends(ObjectId id);

    @Aggregation(pipeline = {
            "{ $match:{ userId : ?0 } }",
            "{ $lookup: {from: 'user', localField: 'friends',foreignField: '_id', as:'result'}}",
            "{$unwind :{path: $result}}",
            "{$replaceRoot :{newRoot: $result}}",
    })
    List<User> getAllFriends(ObjectId id);

    @Aggregation(pipeline = {
            "{ $match:{ userId : ?0 } }",
    })
    Friend addFriend(ObjectId id);
}
