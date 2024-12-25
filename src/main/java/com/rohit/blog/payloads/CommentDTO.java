package com.rohit.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentDTO {

    private Integer id;
    @Column(columnDefinition = "TEXT", nullable = false)
    @NotEmpty(message = "Comment body can not be empty! Write something sane for the love of Internet, would you?")
    private String content;

}
