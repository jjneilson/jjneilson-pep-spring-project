package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("FROM message")
    List<Message> getAllMessages();

    @Query("FROM message where messageId = :messageId")
    Message getMessageById(@Param("messageId") int messageId);

    @Query("FROM message where postedBy = :userId")
    List<Message> getAllMessagesByUserId(@Param("userId") int userId);

    @Query("delete FROM message where messageId = :messageID")
    void deleteMessageById(@Param("messageId") int messageId);

    @Query("UPDATE message SET messageText=:messageText WHERE messageId=:messageId")
    Message updateMessage(@Param("messageId") int messageId, @Param("messageText") String messageText);

}
