//package com.rohit.blog.utils;
//
//import com.rohit.blog.models.BlogUser;
//import com.rohit.blog.payloads.UserDTO;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DtoUserHelper {
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    public BlogUser DtoToUser(UserDTO userDTO){
//        BlogUser newUser;
//        newUser = this.modelMapper.map(userDTO, BlogUser.class);
////        User newUser=new User();
////        newUser.setUserId(userDTO.getUserId());
////        newUser.setName(userDTO.getName());
////        newUser.setEmail(userDTO.getEmail());
////        newUser.setAbout(userDTO.getAbout());
////        newUser.setUserPass(userDTO.getPassword());
//        return newUser;
//    }
//    public UserDTO userToDto(BlogUser newUser){
//        // UserDTO userDTO = new UserDTO();
////        userDTO.setUserId(newUser.getUserId());
////        userDTO.setAbout(newUser.getAbout());
////        userDTO.setName(newUser.getName());
////        userDTO.setEmail(newUser.getEmail());
////        userDTO.setPassword(newUser.getUserPass());
//        return this.modelMapper.map(newUser, UserDTO.class);
//    }
//
//}
