package com.webapp.ui.repository;

import java.util.List;
import com.webapp.ui.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment save(Comment comment);
    List<Comment> findByJobId(int jobId);
}
