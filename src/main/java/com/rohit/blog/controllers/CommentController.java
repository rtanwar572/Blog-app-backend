package com.rohit.blog.controllers;

import com.rohit.blog.payloads.ApiResponse;
import com.rohit.blog.payloads.CommentDTO;
import com.rohit.blog.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Integer postId){
        CommentDTO comment = this.commentService.createComment(commentDTO,postId);
        System.out.println("\u001B[32mSuccess: Comment Done\u001B[0m");
        return  new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> delComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        ApiResponse apiResponse=new ApiResponse("Comment Deleted Successfully !",true,"Resource with ID "+commentId+" has been removed permanently.",null);
//        System.out.println("\033[6m\033[31mImportant message!\033[0m");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
