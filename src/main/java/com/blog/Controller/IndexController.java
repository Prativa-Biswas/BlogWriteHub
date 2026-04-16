package com.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.DTO.BlogResponse;
import com.blog.DTO.CommentForm;
import com.blog.DTO.CommentsResponse;
import com.blog.DTO.IndexResponseDTO;
import com.blog.Service.BlogService;
import com.blog.Service.CommentService;

@Controller
public class IndexController {
	
	@Autowired
	private BlogService service;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		
		List<IndexResponseDTO> allBlog = service.getAllBolgs();
		model.addAttribute("responseForm",allBlog);
		
		return "index";
	}
	
	@GetMapping("/readBlog")
	public String getBlog(@RequestParam("blogId") Integer blogId ,Model model) {
		
		//FETCHING BLOG LIST
		BlogResponse responseBody = service.getBlogById(blogId);
		model.addAttribute("responseBody",responseBody);
		
		//FETCHING COMMENTS 
		List<CommentsResponse> comments = commentService.getAllCommentsByBlogId(blogId);
		model.addAttribute("comments",comments);
		
		//SENDING DATA BINDING FORM FOR COMMENTS 
		CommentForm commentForm = new CommentForm();
		commentForm.setBlogId(blogId);
		model.addAttribute("commentForm", commentForm);
		
		return "viewBlog";
	}

	@GetMapping("/searchBlog")
    public String searchBlog(
    		@RequestParam("keyword") String keyword ,Model model) {
	    
		List<IndexResponseDTO> filteredBlogs = service.findFilteredBlogs(keyword);
		model.addAttribute("responseForm",filteredBlogs);
		
		return "filteredIndex :: blogList";
	}
	
	
}
