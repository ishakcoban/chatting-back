package com.example.chatting.service;


import com.example.chatting.entity.Notification;
import com.example.chatting.modal.response.ConfirmedResponse;
import com.example.chatting.modal.response.RequestsComingFromOtherUsersResponse;
import com.example.chatting.modal.response.ShortButtonStatusResponse;
import com.example.chatting.modal.response.ShortNotificationResponse;
import com.example.chatting.repository.FriendRepository;
import com.example.chatting.repository.NotificationRepository;
import com.example.chatting.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class NotificationService {

    UserRepository userDao;
    NotificationRepository notificationDao;
    FriendRepository friendDao;

    public Notification getNotifications(String id) throws Exception {
        return notificationDao.getNotifications(new ObjectId(id));
    }

    public Notification sendRequest(String userId,String friendId) throws Exception {

        Notification updated = notificationDao.getNotifications(new ObjectId(friendId));
        updated.getRequestsComingFromOtherUsers().add(new RequestsComingFromOtherUsersResponse(new ObjectId(userId),false,false,false,false, LocalDateTime.now()));
        return notificationDao.save(updated);

    }

    public ShortButtonStatusResponse analyseButtonStatus(String userId, String friendId) throws Exception {
        String add = "Add";
        String requested = "Requested";
        String accept = "Accept";


        if(notificationDao.controlUserRequest(new ObjectId(userId),new ObjectId(friendId)) != null){
            return new ShortButtonStatusResponse(accept);
        }else{
            if((notificationDao.controlUserRequest(new ObjectId(friendId),new ObjectId(userId)) != null)){
                return new ShortButtonStatusResponse(requested);
            }else{
                return new ShortButtonStatusResponse(add);
            }
        }

    }
    public Notification deleteRequest(String userId,String friendId) throws Exception {
        Notification updated = notificationDao.getNotifications(new ObjectId(friendId));
        updated.getRequestsComingFromOtherUsers().removeIf(requests -> Objects.equals(requests.getSender(), new ObjectId(userId)));

        return notificationDao.save(updated);
    }

    public Notification acceptRequest(String userId,String friendId) throws Exception {

        deleteRequest(friendId,userId);
        Notification updated = notificationDao.getNotifications(new ObjectId(friendId));
        updated.getConfirmedRequests().add(new ConfirmedResponse(new ObjectId(userId),false,LocalDateTime.now()));
        return notificationDao.save(updated);
    }

    public List<ShortNotificationResponse> concatNotification(String userId) throws Exception {

        return notificationDao.concatAllNotificationTypes(new ObjectId(userId));
    }

    public Notification setTrueAllSeenStatus(String userId) throws Exception {

        Notification updated = getNotifications(userId);
        System.out.println(updated);
        for (int i = 0; i < updated.getConfirmedRequests().size(); i++) {

            updated.getConfirmedRequests().get(i).setSeen(true);
        }

        for (int i = 0; i < updated.getRequestsComingFromOtherUsers().size(); i++) {
            updated.getRequestsComingFromOtherUsers().get(i).setSeen(true);
        }


        return notificationDao.save(updated);
    }
}
