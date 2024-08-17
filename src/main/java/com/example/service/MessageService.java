package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAllMessages(){
        return this.messageRepository.findAll();
    }
 
    public Message findByMessageId(int messageId){
        return this.messageRepository.findByMessageId(messageId);
    }

    public List<Message> findAllMessagesByAccountId(int postedBy){
        return this.messageRepository.findByPostedBy(postedBy);
    }

    public Message createMessage(Message message) {
        return this.messageRepository.save(message);
    }

    @Transactional
    public Message updateMessage(int id, Message message){
        boolean notexists=this.messageRepository.findByMessageId(id)!=null;
        boolean notblank=message.getMessageText().length()>0;
        boolean nottoolong=message.getMessageText().length()<=255;
        if (notexists && notblank && nottoolong) {
            messageRepository.updateMessage(id, message.getMessageText());
            return this.messageRepository.findByMessageId(id);
        }
        return null;
    }

    @Transactional
    public Message deleteByMessageId(int id){
        if(this.messageRepository.findByMessageId(id)!=null){
            Message to_return = this.messageRepository.findByMessageId(id);
            this.messageRepository.deleteByMessageId(id);
            return to_return;
        }
        return null;
    }

}
