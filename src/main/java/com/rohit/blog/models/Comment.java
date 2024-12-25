package com.rohit.blog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Post.class,property = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT", nullable = false)
    @NotEmpty(message = "Comment body can not be empty! Write something sane for the love of Internet, would you?")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "fk-author-id")
    private BlogUser user;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", post_id=" + post.getPostId() + // can't go for post object it self as it will make a recursion and overflow a stack
//                ", username=" + user.getUsername() +
                '}';
    }
}
