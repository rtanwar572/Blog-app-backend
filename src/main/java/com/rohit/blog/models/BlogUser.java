package com.rohit.blog.models;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
////import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
//import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class BlogUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank(message = "Username is required")
//    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,16}$", message = "Invalid username. It must be 3-16 characters long and can only contain letters, digits, '.', '_', or '-'")
    @Column(name = "userName", nullable = false,unique = true)
    private String name;
    @NotEmpty
    private String password;

    @Column(unique = true)
    private String email;
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Comment> comments=new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user",referencedColumnName = "userId"),
    inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id"))
    Set<Role> roles=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList;
        authorityList = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getRole())).toList();
        return authorityList;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
