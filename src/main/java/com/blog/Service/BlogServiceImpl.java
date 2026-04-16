package com.blog.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DTO.BlogPostForm;
import com.blog.DTO.BlogResponse;
import com.blog.DTO.IndexResponseDTO;
import com.blog.Entity.Blog;
import com.blog.Entity.User;
import com.blog.Repository.BlogRepository;
import com.blog.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BlogRepository blogRepo;

	@Override
	public boolean addNewBlog(BlogPostForm form, Integer userId) {
		
		// set the binding form data to entity	
		Blog blog= new Blog();	
		
		if(form.getBlogContent()==null && form.getBlogContent().isBlank())
		{
			return false;
 		}
		
		
		BeanUtils.copyProperties(form, blog);
		
	// Assigning user
      Optional<User> optional = userRepo.findById(userId);
      if(optional.isPresent())
      { 
    	  blog.setUser(optional.get());
      }
      else { return false;}
		// save the blog
		blogRepo.save(blog);
		
		return true;
	}

	@Override
	public List<Blog> getAllBlog(Integer userId) {
		
		User user = userRepo.findById(userId).get();
		 List<Blog> list = blogRepo.findByUser(user);
		
		return list;
	}

	@Transactional
	@Override
	public void delteBlogById(Integer id) {
		
		// check blog available or not
		 Blog blog = blogRepo.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
		
		 //Remove blog from userList
		 User user = blog.getUser();
		 if(user!=null)
		 {
			 user.getBlogList().remove(blog);
		 }
		
		 blogRepo.delete(blog);
	}

	@Override
	public BlogPostForm getBlog(Integer userId, Integer blogId) {

		//Check User Available or not
		Optional<User> byId = userRepo.findById(userId);
		if(byId.isEmpty()) {return null;}
		
		//Create BlogPostForm form
		BlogPostForm editForm = new BlogPostForm();		 
		User user = byId.get();
		List<Blog> blogList = user.getBlogList();
		Optional<Blog> first = blogList.stream().filter(e->e.getBlogId().equals(blogId)).findFirst();
		if(first.isEmpty() ) {return null;}
		Blog blog = first.get();
		editForm.setBlogTitle(blog.getBlogTitle());
		editForm.setShortDescription(blog.getShortDescription());
		editForm.setBlogContent(blog.getBlogContent());
		editForm.setBlogId(blogId);
		
		return editForm;
	}

	@Override
	public String updateBlog(Integer userid, BlogPostForm form) {
		
		Optional<User> byId = userRepo.findById(userid);
		if(byId.isEmpty()) {return "User Not Found";}
		
		List<Blog> blogList = byId.get().getBlogList();
		if(blogList==null && blogList.isEmpty()) {return "User Blogs Not Found";}
		
		Optional<Blog> first = blogList.stream().filter(e-> e.getBlogId().equals(form.getBlogId())).findFirst();
		if(first.isEmpty()) {return "Blog Not Found";}
		
		 // GET EXISTING BLOG (IMPORTANT)
	    Blog blog = first.get();
		
		// UPDATE ONLY REQUIRED FIELDS
		blog.setBlogId(form.getBlogId());
		blog.setBlogTitle(form.getBlogTitle());
		blog.setShortDescription(form.getShortDescription());
		blog.setBlogContent(form.getBlogContent());
		
		blogRepo.save( blog);
		
		return "SUCCESS";
	}

	@Override
	public List<IndexResponseDTO> getAllBolgs() {
			
		//FETCH ALL BLOG
		List<Blog> list = blogRepo.findAll();
		
		//CREATED RESPONSE OBJECT 
		List<IndexResponseDTO > reponseList= list.stream().map(blog->
		{
			IndexResponseDTO res= new IndexResponseDTO();
			res.setTitle(blog.getBlogTitle());
			res.setCreationDate(blog.getCreationDate());
			res.setShortDesc(blog.getShortDescription());
			res.setBlogId(blog.getBlogId());
			return res;
		}).collect(Collectors.toList());
			
		
		return reponseList;
	}

	@Override
	public BlogResponse getBlogById(Integer Id) {

		Optional<Blog> optionalBlog = blogRepo.findById(Id);
		if(optionalBlog.isEmpty()) {return null;}
		BlogResponse res= new BlogResponse();
		Blog blog = optionalBlog.get();
		res.setTitle(blog.getBlogTitle());
		res.setShortDesc(blog.getShortDescription());
		res.setContent(blog.getBlogContent());
		return res;
	}

	@Override
	public List<IndexResponseDTO> findFilteredBlogs(String keyword) {

		List<Blog> list = blogRepo.findFilteredBlogs(keyword);		
		List<IndexResponseDTO> collect = list.stream().map(blog->{
			
			IndexResponseDTO res = new IndexResponseDTO();
			res.setBlogId(blog.getBlogId());
			res.setShortDesc(blog.getShortDescription());
			res.setTitle(blog.getBlogTitle());
			res.setCreationDate(blog.getCreationDate());
			
			return res;
		}).collect(Collectors.toList());
		
		
		
		return collect;
	}

	@Override
	public List<Blog> getUserFilteredBlogs(String keyword,Integer userId) {

		List<Blog> filteredBlogs = blogRepo.findFilteredBlogs(keyword);
		List<Blog> userBlogList = filteredBlogs.stream().filter(blog->blog.getUser().getUserId().equals(userId)).collect(Collectors.toList());
		
		return userBlogList;
	}

	
}
