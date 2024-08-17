package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@Controller
public class SocialMediaController {
    
    @Autowired
    MessageService messageService;
    AccountService accountService;

    @Autowired
    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @GetMapping("/messages")
    public ResponseEntity findAllMessages(){
        return ResponseEntity.status(200).body(messageService.findAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity findByMessageId(@PathVariable int messageId){
        return ResponseEntity.status(200).body(messageService.findByMessageId(messageId));
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity findAllMessagesByUserId(@PathVariable int accountId){
        return ResponseEntity.status(200).body(messageService.findAllMessagesByAccountId(accountId));
    }

    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message){
        boolean exists = accountService.findByAccountId(message.getPostedBy())!=null;
        boolean notblank=message.getMessageText().length()>0;
        boolean nottoolong=message.getMessageText().length()<=255;
        if(exists && notblank && nottoolong){
            Message response = messageService.createMessage(message);
            return ResponseEntity.status(200).body(response);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }  
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessage(@PathVariable int messageId, @RequestBody Message message){
        Message response = messageService.updateMessage(messageId, message);
        if(response!=null){
            return ResponseEntity.status(200).body(1);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessage(@PathVariable int messageId){
        Message response = messageService.deleteByMessageId(messageId);
        if(response!=null){
            return ResponseEntity.status(200).body(1);
        }
        else{
            return ResponseEntity.status(200).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Account newAccount){
        boolean notexists=accountService.findByUsername(newAccount.getUsername())==null;
        boolean notblank=newAccount.getUsername().length()>0;
        boolean nottooshort=newAccount.getPassword().length()>4;
        if (notexists && notblank && nottooshort) {
            Account response = accountService.registerAccount(newAccount);
            return ResponseEntity.status(200).body(response);
        }
        else{
            if(!notexists){
                return ResponseEntity.status(409).body(null);
            }
            else{
                return ResponseEntity.status(400).body(null);
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Account account){
        Account response = this.accountService.loginUser(account);
        if(response!=null){
            return ResponseEntity.status(200).body(response);
        }
        else{
            return ResponseEntity.status(401).body(null);
        }
    }

}
