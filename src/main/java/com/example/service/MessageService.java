package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return this.messageRepository.getAllMessages();
    }

    public Message getMessageById(int messageId){
        return this.messageRepository.getMessageById(messageId);
    }

    public Message addMessage(Message message) {
        boolean notexists=this.messageRepository.getMessageById(message.getMessageId())==null;
        boolean notblank=message.getMessageText().length()>0;
        boolean nottoolong=message.getMessageText().length()<=255;
        if (notexists && notblank && nottoolong) return this.messageRepository.save(message);
        return null;
    }

    public Message deleteMessage(int id){
        if(this.messageRepository.getMessageById(id)!=null){
            Message to_return = this.messageRepository.getMessageById(id);
            this.messageRepository.deleteMessageById(id);
            return to_return;
        }
        return null;
    }

    public Message updateMessage(int id, Message message){
        boolean notexists=this.messageRepository.getMessageById(id)!=null;
        boolean notblank=message.getMessageText().length()>0;
        boolean nottoolong=message.getMessageText().length()<=255;
        if (notexists && notblank && nottoolong) {
            messageRepository.updateMessage(id, message.getMessageText());
            return this.messageRepository.getMessageById(id);
        }
        return null;
    }

    public List<Message> getAllMessagesByUserId(int userId){
        return this.messageRepository.getAllMessagesByUserId(userId);
    }
}
