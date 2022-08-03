package com.example.messaging.repository;

import com.example.messaging.modal.Participant;
import com.example.messaging.modal.User;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParticipantDao extends MongoRepository<Participant,String> {

    @Aggregation(pipeline = {
            "{ $match:{ participants: { $all:[ { $elemMatch: { $in: [?0]  } }, { $elemMatch: { $in: [?1]  } } ] } } }",
            //"{ $unwind: {'path': $messages}}",
            //"{ $replaceRoot: {newRoot: $messages}}",
    })
    Participant getMessages(ObjectId id1, ObjectId id2);

    @Aggregation(pipeline = {
            "{ $match:{ participants: { $all:[ { $elemMatch: { $in: [?0]  } }] } } }",
            "{ $lookup: {from: 'user', localField: 'participants',foreignField: '_id', as:'participants'}}",
            "{ $sort:{ 'lastMessageDate' : -1}}"

    })
    List<Participant> getAllChatList(ObjectId id);
}
