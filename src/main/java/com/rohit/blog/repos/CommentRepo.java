package com.rohit.blog.repos;

import com.rohit.blog.models.Comment;
import com.rohit.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {

    Set<Comment> findByPost(Post post);
}
