package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.Entity.Blog;
import com.blog.Entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	public List<Blog> findByUser(User user);
	
	@Query("SELECT b FROM Blog b WHERE "
			+ "LOWER(b.blogTitle) LIKE LOWER(CONCAT('%', :keyword,'%')) OR "
			+ "LOWER(b.shortDescription) LIKE LOWER(CONCAT('%', :keyword,'%')) OR "
			+ "b.blogContent LIKE CONCAT('%', :keyword,'%')")
	public List<Blog> findFilteredBlogs(@Param("keyword") String keyword);
	
}

