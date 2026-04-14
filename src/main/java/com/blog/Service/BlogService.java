package com.blog.Service;

import java.util.List;

import com.blog.DTO.BlogPostForm;
import com.blog.Entity.Blog;

public interface BlogService {

	public boolean addNewBlog(BlogPostForm form, Integer userId);
	public List<Blog> getAllBlog(Integer userId);
	
	public void delteBlogById(Integer id);
	public BlogPostForm getBlog(Integer userId, Integer blogId);
	public String updateBlog(Integer userid,BlogPostForm form);
}
