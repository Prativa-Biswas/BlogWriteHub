package com.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Entity.Blog;
import com.blog.Entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	public List<Blog> findByUser(User user);
}

