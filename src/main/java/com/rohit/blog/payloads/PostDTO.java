package com.rohit.blog.payloads;

//import com.rohit.blog.models.Comment;
import com.rohit.blog.models.Comment;
import com.rohit.blog.models.Enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {
    private Integer postId;
    @NotEmpty(message = "Title Couldn't Be Empty Or Null")
    private String title;
    @NotEmpty
    @Size(min = 4,message = "Content Must Have At-least 4 Chars" )
    private String content;
    @Enumerated(value = EnumType.ORDINAL)
    private Category category;
    @Pattern(regexp = "^(.*\\.jpeg|.*\\.jpg|.*\\.png|.*\\.mp4)$", message = "Invalid image format. Allowed formats: .jpeg, .jpg, .png, .mp4")
    private String image;
    //for avoiding infinite loop just use User dto instead of user
    private UserDTO user;

    private Set<CommentDTO> comments;


}
