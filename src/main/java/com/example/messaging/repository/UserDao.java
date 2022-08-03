package com.example.messaging.repository;

import com.example.messaging.modal.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface UserDao extends MongoRepository<User,String> {

    @Query("{'email' : ?0}")
    Optional<User> findByEmail(String email);

    @Query("{'id' : mnm}")
    List<User> exceptMe1(@Param("mnm") ObjectId id);

    @Aggregation(pipeline = {
            "{ $match:{ '_id' : {$ne : '$mnm'}}}"
    })
    List<User> exceptMe(@Param("mnm") ObjectId id);

}
