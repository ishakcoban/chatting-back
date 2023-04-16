package com.example.chatting.repository;

import com.example.chatting.entity.Notification;
import com.example.chatting.modal.response.ShortButtonStatusResponse;
import com.example.chatting.modal.response.ShortNotificationResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,String > {

    @Aggregation(pipeline = {
            "{ $match:{ 'userId' : ?0 } }",
    })
    Notification getNotifications(ObjectId id);

    @Aggregation(pipeline = {
            "{ $match:{ 'userId' : ?0 } }",
            "{ $unwind: {path: $requestsComingFromOtherUsers}}",
            "{ $replaceRoot: {newRoot: $requestsComingFromOtherUsers}}",
            "{ $match: {'sender': ?1}}",
    })
    ShortButtonStatusResponse controlUserRequest(ObjectId id1, ObjectId id2);

    @Aggregation(pipeline = {
            "{ $match:{ 'userId' : ?0 } }",
            "{ $project: {items: { $concatArrays: [ $requestsComingFromOtherUsers, $confirmedRequests ] }}}",
            "{ $unwind:{path: $items}}",
            "{ $replaceRoot:{newRoot: $items}}",
            "{ $sort: {date: -1}}",
            "{ $lookup: {from: 'user', localField: 'sender',foreignField: '_id', as: 'sender'}}",
            "{ $unwind:{path: $sender}}",
    })
    List<ShortNotificationResponse> concatAllNotificationTypes(ObjectId id1);

}
