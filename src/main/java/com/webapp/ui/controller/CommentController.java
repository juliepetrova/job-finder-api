package com.webapp.ui.controller;

import com.webapp.ui.model.Comment;
import com.webapp.ui.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @MessageMapping("/comment")
    @SendTo("/topic/comments")
    public Comment comment(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Comment comment = new Comment();
        String[] parts = message.split("_");
        comment.setComment(parts[0]);
        comment.setJob_id(Integer.parseInt(parts[1]));
        comment.setDate(dtf.format(now));
        commentRepository.save(comment);
        return comment;
    }

}
