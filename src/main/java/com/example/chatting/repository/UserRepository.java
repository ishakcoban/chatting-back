package com.example.chatting.repository;


import com.example.chatting.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    @Query("{'email' : ?0}")
    Optional<User> findByEmail(String email);

    @Query("{'id' : mnm}")
    List<User> exceptMe1(@Param("mnm") ObjectId id);

    @Aggregation(pipeline = {
            "{ $match:{ '_id' : {$ne : '$mnm'}}}"
    })
    List<User> exceptMe(@Param("mnm") ObjectId id);

    boolean existsByEmail(String email);

}
