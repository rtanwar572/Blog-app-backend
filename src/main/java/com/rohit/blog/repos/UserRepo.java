package com.rohit.blog.repos;

import com.rohit.blog.models.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<BlogUser, Integer> {

    Optional<BlogUser> findByName(String username);
}
