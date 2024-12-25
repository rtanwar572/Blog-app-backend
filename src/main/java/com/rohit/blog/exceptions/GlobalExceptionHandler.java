package com.rohit.blog.exceptions;

import com.rohit.blog.payloads.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

//inside of that all the  exception handler methods are present
@RestControllerAdvice
public class GlobalExceptionHandler {
    //which type of exception we are handling we have to specify that in handlder annotation
    //whenever ResourceNotFoundException is occur this methode will call
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExpHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        System.err.println("\u001B[31mError: Resource Not Found\u001B[0m"); //red Colored error on console

        ApiResponse response = new ApiResponse(
                message,
                false,
                "The resource you are trying to access does not exist or has been removed.",
                "RESOURCE_NOT_FOUND"
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    //when constrains violate
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> map=new HashMap<>();
        System.err.println("\u001B[31mError: Methode Argument is Not Valid =>    Write In Valid Formate)\u001B[0m"); //red Colored error on console
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            map.put(fieldName,message);
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        System.err.println("\u001B[31mError:  InValid input =>    Write In Valid Formate)\u001B[0m"); //red Colored error on console
        return ResponseEntity.badRequest().body(new ApiResponse(
                "Invalid input provided.",
                false,
                ex.getMessage(),
                "INVALID_INPUT"
        ));
    }
    //If Invalid Input Entered then this methode will call when IllegalArgumentException occured
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        System.err.println("\u001B[31mError: Invalid input provided\u001B[0m"); //red Colored error on console

        return ResponseEntity.badRequest().body(new ApiResponse(
                "Invalid input provided.",
                false,
                ex.getMessage(),
                "INVALID_INPUT"
        ));
    }
    @ExceptionHandler(NonTransientDataAccessException.class)
    public ResponseEntity<Map<String,String>> handleNonTransientDataAccessException(NonTransientDataAccessException ex) {
        Map<String,String> map;
        String message=ex.getMessage();
        map = extractConstraintDetails(message);
        map.put("Constrains Voilation: ", message);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    private Map<String,String> extractConstraintDetails(String message) {
        System.err.println("\u001B[31mError: Constrains Voilation Or Duplicate Entry .\u001B[0m"); //red Colored error on console
        Map<String,String> map=new HashMap<>();
        String err="";
        if (message.contains("foreign key")) {
            err="A foreign key constraint violation occurred. Please ensure related data exists.";
            map.put("Database error:",err);
        }
        if (message.contains("duplicate key") || message.contains("Duplicate entry")) {
            err="A unique constraint violation occurred. Please ensure no duplicate data.";
            map.put("Database error:",err);
        }
        return map;
    }


    //general exception like 500 internal server error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
        System.err.println("\u001B[31mError: An Unexpected Error Occurred ->  TRY AGAIN LATER.\u001B[0m"); //red Colored error on console
        ApiResponse response = new ApiResponse(
                "An unexpected error occurred. Please try again later.",
                false,
                ex.getMessage(),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
