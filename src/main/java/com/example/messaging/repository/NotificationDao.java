package com.example.messaging.repository;

import com.example.messaging.modal.Notification;
import com.example.messaging.modal.User;
import com.example.messaging.modal.response.ShortButtonStatusResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationDao extends MongoRepository<Notification,String > {

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
    ShortButtonStatusResponse controlUserRequest(ObjectId id1, String id2);

    @Aggregation(pipeline = {
            "{ $match:{ 'userId' : ?0 } }",
            "{ $project: {items: { $concatArrays: [ $requestsComingFromOtherUsers, $confirmedRequests ] }}}",
            "{ $unwind:{path: $items}}",
            "{ $sort: {'date': -1}}",
    })
    List<Object> concatAllNotificationTypes(ObjectId id1);

}
