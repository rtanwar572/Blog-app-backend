package com.rohit.blog.services;

import com.rohit.blog.exceptions.ResourceNotFoundException;
import com.rohit.blog.models.BlogUser;
import com.rohit.blog.models.Comment;
import com.rohit.blog.models.Post;
import com.rohit.blog.payloads.CommentDTO;
import com.rohit.blog.repos.CommentRepo;
import com.rohit.blog.repos.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private PostRepo  postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        // Fetch the post from the repository
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        BlogUser user = post.getUser();
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        comment.setCreationDate(new Date());
        comment.setUser(user);
        Comment savedComment = commentRepo.save(comment);
//        System.out.println(savedComment.getPost().get); // for testing purpose
        return this.modelMapper.map(savedComment,CommentDTO.class);
    }


    public void deleteComment(Integer commentId) {
        Comment  comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
        this.commentRepo.delete(comment);
    }
}
