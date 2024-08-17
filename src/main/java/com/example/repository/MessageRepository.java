package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    Message findByMessageId(int messageId);

    List<Message> findByPostedBy(int postedBy);

    @Modifying
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    void updateMessage(@Param("messageId") Integer messageId, @Param("messageText") String messageText);

    @Modifying
    @Query("delete from Message m where m.messageId=:messageId")
    void deleteByMessageId(@Param("messageId") int messageId);

}
