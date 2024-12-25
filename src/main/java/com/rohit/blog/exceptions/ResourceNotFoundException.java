package com.rohit.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

   String resourceName;
   String fieldName;
   long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s: %d", resourceName, fieldName, fieldValue));
        System.err.println("\u001B[31mError: Resource not found\u001B[0m"); //red Colored error on console
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
