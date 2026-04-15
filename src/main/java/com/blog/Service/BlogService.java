package com.blog.Service;

import java.util.List;

import com.blog.DTO.BlogPostForm;
import com.blog.DTO.BlogResponse;
import com.blog.DTO.IndexResponseDTO;
import com.blog.Entity.Blog;

public interface BlogService {

	public List<IndexResponseDTO>  getAllBolgs();

	public boolean addNewBlog(BlogPostForm form, Integer userId);
	public List<Blog> getAllBlog(Integer userId);
	
	public void delteBlogById(Integer id);
	public BlogPostForm getBlog(Integer userId, Integer blogId);
	public String updateBlog(Integer userid,BlogPostForm form);
	
	public BlogResponse getBlogById(Integer Id);
}
