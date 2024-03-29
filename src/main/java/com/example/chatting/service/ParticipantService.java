package com.example.chatting.service;

import com.example.chatting.entity.Participant;
import com.example.chatting.modal.response.MessageResponse;
import com.example.chatting.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ParticipantService {
    ParticipantRepository participantDao;

    public Participant getMessages(Participant participant){

        return participantDao.getMessages(new ObjectId(participant.getParticipants().get(0).toString()),new ObjectId(participant.getParticipants().get(1).toString()));

    }
    public Participant sendMessage(Participant participant,String sender) throws Exception {
        System.out.println(participantDao.getMessages(new ObjectId(participant.getParticipants().get(0).toString()),new ObjectId(participant.getParticipants().get(1).toString())).getParticipants().get(0));

        Participant updated = participantDao.getMessages(new ObjectId(participant.getParticipants().get(0).toString()),new ObjectId(participant.getParticipants().get(1).toString()));
        updated.getMessages().add(new MessageResponse(new ObjectId().toString(),sender,participant.getMessages().get(0).getContent(), LocalDateTime.now()));
        updated.setLastMessageDate(updated.getMessages().get(updated.getMessages().size()-1).getDateTime());
        return participantDao.save(updated);
    }

    public List<Participant> getAllChatList(String id){

        return participantDao.getAllChatList(new ObjectId(id));

    }

}
