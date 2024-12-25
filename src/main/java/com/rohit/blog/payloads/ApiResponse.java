package com.rohit.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;        // The error or success message
    private boolean success;       // Indicates the outcome
    private LocalDateTime timestamp;  // When the response was created
    private String details;        // Additional details about the exception
    private String errorCode;      // Custom error code (optional)

    // Constructor
    public ApiResponse(String message, boolean success, String details, String errorCode) {
        this.message = message;
        this.success = success;
        this.timestamp = LocalDateTime.now(); // Auto-generate timestamp
        this.details = details;
        this.errorCode = errorCode;
    }
}
