package com.dh.blogapi.Interfaces;

import com.dh.blogapi.Models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog , Integer> {
    @Query(value = "SELECT * FROM blogs WHERE status = :status" , nativeQuery = true)
    List<Blog> findBlogsByStatus(@Param("status") String status );

    @Query(value = "UPDATE blogs SET status = :status WHERE id = :id" , nativeQuery = true)
    void updateStatus( @Param("id") Integer id, @Param("status") String status);

    @Query(value = "SELECT * FROM blogs WHERE status = :status AND id = :id" , nativeQuery = true)
    Blog findBlogByStatus(Integer id, String status);

    @Query(value = "SELECT * FROM blogs WHERE owner = :owner" , nativeQuery = true)
    List<Blog> findBlogsByOwner(@Param("owner") String owner);

    @Modifying
    @Query(value = "" , nativeQuery = true)
    @Transactional
    void updateBlogVote( @Param("vote") String vote , @Param("blogId") Integer blogId );

    @Query(value = "SELECT COUNT(vote) FROM blogs_vote_summery WHERE vote = 1 AND blogId = :blogId" , nativeQuery = true)
    Integer countLikeVotes( @Param("blogId") Integer blogId );

    @Query(value = "SELECT COUNT(vote) FROM blogs_vote_summery WHERE vote = -1 AND blogId = :blogId" , nativeQuery = true)
    Integer countDisLikeVotes( @Param("blogId") Integer blogId );

    @Query(value = "SELECT vote FROM blogs_vote_summery WHERE blogId = :blogId AND username = :username")
    Integer getVoteByUser(@Param("blogId") Integer blogId , @Param("username") String username);


}
