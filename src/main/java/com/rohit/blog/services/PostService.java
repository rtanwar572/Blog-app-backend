package com.rohit.blog.services;

import com.rohit.blog.exceptions.ResourceNotFoundException;
import com.rohit.blog.models.Comment;
import com.rohit.blog.models.Enums.Category;
import com.rohit.blog.models.Post;
import com.rohit.blog.models.BlogUser;
import com.rohit.blog.payloads.PostDTO;
import com.rohit.blog.payloads.PostResponse;
import com.rohit.blog.repos.CommentRepo;
import com.rohit.blog.repos.PostRepo;
import com.rohit.blog.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PostService {

    final String defaultImgUrl="default-image.png";

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    public PostDTO createPost(PostDTO postDTO, Integer userId){
        BlogUser user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Post newPost=this.modelMapper.map(postDTO,Post.class);

        newPost.setImage(defaultImgUrl);
        newPost.setUpdationDate(new Date());
        newPost.setUser(user);
        newPost.setComments(null);
        Post addedPost = this.postRepo.save(newPost);
        return this.modelMapper.map(addedPost,PostDTO.class);
    }


    public PostResponse getAllPostByCategory(Category category,int pageNumber,int pageSize,String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(Sort.Order.asc(sortBy)):Sort.by(Sort.Order.desc(sortBy));

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost=postRepo.findAllByCategory(category,p);
        final List<Post> postList = pagePost.getContent();
        List<PostDTO> postDTOList;
        postDTOList = postList.stream().map((post -> this.modelMapper.map(post,PostDTO.class)) ).toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalRecords(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    public PostResponse getAllPostByUser(Integer userId,int pageNumber,int pageSize,String sortBy ,String sortDir) {
        BlogUser user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        //enhanced Of methode used
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(Sort.Order.asc(sortBy)):Sort.by(Sort.Order.desc(sortBy));
        Pageable p=PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost=postRepo.findAllByUser(user,p);
        List<Post> postList=pagePost.getContent();
        List<PostDTO> postDTOList;
        postDTOList = postList.stream().map((post) -> this.modelMapper.map(post,PostDTO.class)).toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalRecords(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    public PostDTO getPostById(Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        Set<Comment> commentSet = this.commentRepo.findByPost(post);
//        System.out.println(commentSet.toString());
//        for (Comment cm:commentSet){
//            System.out.println("this is the content: "+cm.getContent());
//        }
// for testing purpose
        post.setComments(commentSet);
        return this.modelMapper.map(post,PostDTO.class);
    }

    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(Sort.Order.asc(sortBy)):Sort.by(Sort.Order.desc(sortBy));

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost=this.postRepo.findAll(p);
        final List<Post> postList = pagePost.getContent();
        List<PostDTO> postDTOList;
        postDTOList = postList.stream().map((post -> this.modelMapper.map(post,PostDTO.class)) ).toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalRecords(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUpdationDate(new Date());
//        post.setImage(postDTO.getImage());
        Post updatedPost=postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDTO.class);
    }

    public void delPost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        this.postRepo.delete(post);
    }

    public List<PostDTO> searchPostsByKeyword(String keyword) {
        List<Post> postList=this.postRepo.findByTitleContaining(keyword);
        return postList.stream().map((post)-> this.modelMapper.map(post,PostDTO.class)).toList();
    }
}
