package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Entity.Blog;
import com.blog.Entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
	
	public List<Comments> findByBlog(Blog blog);

}
