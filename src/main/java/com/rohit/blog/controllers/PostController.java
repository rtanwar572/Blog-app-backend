package com.rohit.blog.controllers;


import com.rohit.blog.config.AppConstants;
import com.rohit.blog.models.Enums.Category;
import com.rohit.blog.payloads.ApiResponse;
import com.rohit.blog.payloads.FileResponse;
import com.rohit.blog.payloads.PostDTO;
import com.rohit.blog.payloads.PostResponse;
import com.rohit.blog.services.FileService;
import com.rohit.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    //file service
    @Autowired
    FileService fileService;
    @Value("${project.image}")
    private String path;

    //get post by its ID
    @GetMapping("/posts/{postId}")
    private ResponseEntity<PostDTO> getPost(@PathVariable Integer postId){
        PostDTO postDTO=postService.getPostById(postId);

        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }
    //get all posts
    @GetMapping("/posts")
    private ResponseEntity<PostResponse> getAllPosts(
       @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
       @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_Size,required = false) Integer pageSize,
       @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
       @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_Dir,required = false) String sortDir

    )
    {
        PostResponse postResponse=postService.getAllPosts(pageNumber, pageSize,sortBy,sortDir);
        return ResponseEntity.ok(postResponse);
    }
    //get posts by userId
    @GetMapping("/user/{userId}/posts")
    private ResponseEntity<PostResponse> getAllPostByUser(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_Size,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_Dir,required = false) String sortDir
    ){
        PostResponse postDTOList=postService.getAllPostByUser(userId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
    //get posts by category
    @GetMapping("/category/{category}/posts")
    private ResponseEntity<PostResponse> getAllPostByCategory(
            @PathVariable Category category,
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_Size,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_Dir,required = false) String sortDir
    ){
        PostResponse postDTOList=postService.getAllPostByCategory(category,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
    //Search Posts By Keyword
    @GetMapping("/search/posts/{keyword}")
    private ResponseEntity<List<PostDTO>> searchPostsByKeyword(@PathVariable String keyword){
        List<PostDTO> postDTOList=postService.searchPostsByKeyword(keyword);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }
    //create post for particular user
    @PostMapping("/user/{userId}/posts")
    private ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId){
        PostDTO createdPost = this.postService.createPost(postDTO, userId);
        System.out.println("\u001B[32mSuccess: Post Created\u001B[0m");
        return new ResponseEntity<>(createdPost, HttpStatusCode.valueOf(201));
    }
    //update Post
    @PutMapping("/posts/{postId}")
    private ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer postId){
        PostDTO updatePost=postService.updatePost(postDTO,postId);
        System.out.println("\u001B[36mSuccess: Post Updated\u001B[0m");
        return new ResponseEntity<>(updatePost,HttpStatus.CREATED);
    }
    //delete Post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> delPost(@PathVariable Integer postId){
        this.postService.delPost(postId);
        return new ResponseEntity<>(new ApiResponse("Resource Deleted Successfully !",true,"Resource with ID "+postId+" has been removed permanently.",null), HttpStatus.OK);
    }

    //files/images related endpoints
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<FileResponse> uploadPostImage(@PathVariable Integer postId, @RequestParam("file") MultipartFile file) {
        try {
            PostDTO postDTO=this.postService.getPostById(postId);
            String imageUrl = fileService.uploadImg(path, file);
            System.out.println("\u001B[32mSuccess: Image Uploaded\u001B[0m");
            postDTO.setImage(imageUrl);
            PostDTO updatedPost = this.postService.updatePost(postDTO, postId);
            return ResponseEntity.ok(new FileResponse(imageUrl,"File Uploaded Successfully !!",updatedPost));
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(null,"Error While Uploading File !!",null),HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImg(@PathVariable("imageName") String image, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path,image);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }



}
