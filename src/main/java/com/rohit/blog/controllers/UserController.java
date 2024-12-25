package com.rohit.blog.controllers;

import com.rohit.blog.payloads.ApiResponse;
import com.rohit.blog.payloads.UserDTO;
import com.rohit.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOList=userService.getAllUsers();
        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> newUser(@Valid @RequestBody UserDTO userDTO){
       UserDTO createdUserDto = this.userService.createUser(userDTO);
       //ANSI escape codes to highlight logs for success and error responses
        System.out.println("\u001B[32mSuccess: Resource Created\u001B[0m");
        return new ResponseEntity<>(createdUserDto, HttpStatusCode.valueOf(201)) ;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId){
        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        System.out.println("\u001B[36mSuccess: Resource Updated\u001B[0m");
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> delUser(@PathVariable("userId") Integer id){
      this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("Resource Deleted Successfully !",true,"Resource with ID "+id+" has been removed permanently.",null), HttpStatus.OK);
    }


}
