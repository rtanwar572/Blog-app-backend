package com.rohit.blog.services;

import com.rohit.blog.exceptions.*;
import com.rohit.blog.models.BlogUser;
import com.rohit.blog.payloads.UserDTO;
import com.rohit.blog.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO getUserById(Integer userId) {
        BlogUser user=userRepo.findById(userId).orElseThrow(() ->new ResourceNotFoundException("User"," Id ",userId));
        return this.modelMapper.map(user,UserDTO.class);
    }

    public UserDTO createUser(UserDTO userDTO) {
        BlogUser newUser = this.modelMapper.map(userDTO, BlogUser.class);
        BlogUser savedUser = userRepo.save(newUser);
        return  this.modelMapper.map(savedUser,UserDTO.class);
    }


    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        //we have nothing in supplier.... of custom exception
        BlogUser newUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," Id ", userId) );
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        BlogUser updatedUser=this.userRepo.save(newUser);
        return this.modelMapper.map(updatedUser,UserDTO.class);
    }

    public void deleteUser(Integer id) {
        BlogUser user=userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
        this.userRepo.delete(user);
    }

    public List<UserDTO> getAllUsers() {
        List<BlogUser> userList = this.userRepo.findAll();
//          userDTOList;
        //  List<UserDTO> userDTOList=new ArrayList<>() ;
//        for (User user:userList){
//            userDTOList.add(dtoUserHelper.userToDto(user));
//        }
        return userList.stream().map(user -> this.modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
    }
}
