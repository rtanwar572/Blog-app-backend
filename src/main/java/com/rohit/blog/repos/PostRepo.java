package com.rohit.blog.repos;

import com.rohit.blog.models.Enums.Category;
import com.rohit.blog.models.Post;
import com.rohit.blog.models.BlogUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    //    List<Post> findByUser(User user);

    Page<Post> findAllByUser(BlogUser user, Pageable p);

    List<Post> findByTitleContaining(String keyword);

    Page<Post> findAllByCategory(Category category, Pageable p);
}
