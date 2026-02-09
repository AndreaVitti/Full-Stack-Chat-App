package com.projects.chatApp.repository;

import com.projects.chatApp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMsgRepository extends JpaRepository<Message, Long> {
}
