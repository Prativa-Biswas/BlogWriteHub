package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.Entity.Blog;
import com.blog.Entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
	
	public List<Comments> findByBlog(Blog blog);
	
	@Query("SELECT c FROM Comments c WHERE c.blog.user.userId= :userId")
	public List<Comments> findAllCommentsByUser(Integer userId );

}
