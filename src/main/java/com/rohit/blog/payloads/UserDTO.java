package com.rohit.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Integer userId;
    @Column(name = "userName", nullable = false,unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,16}$", message = "Invalid username. It must be 3-16 characters long and can only contain letters, digits, '.', '_', or '-'")
    private String name;
    @NotEmpty()
    @Length(min = 5,max = 10,message = "Password length must be min of 4 chars")
    private String password;
    @Email(message = "Invalid Email Entered")
    @Column(unique = true)
    private String email;
    private String about;
}
