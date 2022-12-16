package com.example.appnews.repository;

import com.example.appnews.entity.Comment;
import com.example.appnews.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
