package com.rohit.blog.models;

//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.rohit.blog.models.Enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "posts")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Post.class,property = "postId")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer postId;
    @NotBlank
    @Size(min = 4,message = "Title Should Be Min Of 5 And Max Of 20 Chars")
    private String title;
    @NotEmpty(message = "Write something for the love of Internet...")
    @Column(length = 500,   name = "body", columnDefinition = "TEXT", nullable = false,unique = true)
    private String content;
    @NotEmpty
    private String image;
    @CreationTimestamp //	Automatically sets date
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updation_date", nullable = false)
    private Date updationDate;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "fk-author-id")
    private BlogUser user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

}
