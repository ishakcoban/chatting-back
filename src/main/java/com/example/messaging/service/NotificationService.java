package com.example.messaging.service;

import com.example.messaging.modal.Notification;
import com.example.messaging.modal.User;
import com.example.messaging.modal.response.ConfirmFriendRequest;
import com.example.messaging.modal.response.NotificationRequest;
import com.example.messaging.modal.response.ShortButtonStatusResponse;
import com.example.messaging.repository.FriendDao;
import com.example.messaging.repository.NotificationDao;
import com.example.messaging.repository.UserDao;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationService {

    UserDao userDao;
    NotificationDao notificationDao;
    FriendDao friendDao;

    public Notification getNotifications(String id) throws Exception {
        return notificationDao.getNotifications(new ObjectId(id));
    }

    public Notification sendRequest(String userId,String friendId) throws Exception {

        Notification updated = notificationDao.getNotifications(new ObjectId(friendId));
        updated.getRequestsComingFromOtherUsers().add(new NotificationRequest(userId,false,false,false,false, LocalDateTime.now()));
        return notificationDao.save(updated);

    }

    public ShortButtonStatusResponse analyseButtonStatus(String userId,String friendId) throws Exception {
        String add = "Add";
        String requested = "Requested";
        String accept = "Accept";


        if(notificationDao.controlUserRequest(new ObjectId(userId),friendId) != null){
            return new ShortButtonStatusResponse(accept);
        }else{
            if((notificationDao.controlUserRequest(new ObjectId(friendId),userId) != null)){
                return new ShortButtonStatusResponse(requested);
            }else{
                return new ShortButtonStatusResponse(add);
            }
        }

    }
    public Notification deleteRequest(String userId,String friendId) throws Exception {
        Notification updated = notificationDao.getNotifications(new ObjectId(friendId));
        updated.getRequestsComingFromOtherUsers().removeIf(requests -> Objects.equals(requests.getSender(), userId));

        return notificationDao.save(updated);
    }

    public Notification acceptRequest(String userId,String friendId) throws Exception {

        deleteRequest(friendId,userId);
        Notification updated = notificationDao.getNotifications(new ObjectId(friendId));
        updated.getConfirmedRequests().add(new ConfirmFriendRequest(userId,false,LocalDateTime.now()));
        return notificationDao.save(updated);
    }

    public List<Object> concatNotification(String userId) throws Exception {

        return notificationDao.concatAllNotificationTypes(new ObjectId(userId));
    }
}
