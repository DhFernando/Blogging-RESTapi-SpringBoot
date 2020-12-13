package com.dh.blogapi.Interfaces;

import com.dh.blogapi.Models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog , Integer> {
    @Query(value = "SELECT * FROM blogs WHERE status = :status" , nativeQuery = true)
    List<Blog> findBlogsByStatus(@Param("status") String status );

    @Query(value = "UPDATE blogs SET status = :status WHERE id = :id" , nativeQuery = true)
    void updateStatus( @Param("id") Integer id, @Param("status") String status);

    @Query(value = "SELECT * FROM blogs WHERE status = :status AND id = :id" , nativeQuery = true)
    Blog findBlogByStatus(Integer id, String status);

    @Query(value = "SELECT * FROM blogs WHERE username = :username" , nativeQuery = true)
    List<Blog> findBlogsByUsername(@Param("username") String username);
}
