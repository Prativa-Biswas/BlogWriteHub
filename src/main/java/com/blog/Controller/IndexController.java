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
	
	/**
	 * Handles the request to load the home page (index page).
	 * Retrieves all blogs from the service layer and adds them to the model
	 * with the attribute name "responseForm" for display in the view.
	 *
	 * @param model the Model object used to pass data to the view
	 * @return the name of the index view page
	 */
	@GetMapping("/")
	public String getIndexPage(Model model) {
		
		List<IndexResponseDTO> allBlog = service.getAllBolgs();
		model.addAttribute("responseForm",allBlog);
		
		return "index";
	}
	
	/**
	 * Handles request to display a single blog post along with its comments.
	 * Fetches blog details by blogId, retrieves all comments for the blog,
	 * and prepares a comment form for adding new comments.
	 *
	 * @param blogId the ID of the blog to be displayed
	 * @param model the Model object used to pass data to the view
	 * @return the view name "viewBlog" to render blog details page
	 */
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

	/**
	 * Handles AJAX request for searching blogs based on a keyword.
	 * Retrieves filtered blog list matching the keyword from the service layer
	 * and returns a Thymeleaf fragment for dynamic page update.
	 *
	 * @param keyword the search keyword entered by the user
	 * @param model the Model object used to pass filtered blog data to the view
	 * @return the Thymeleaf fragment "filteredIndex :: blogList" containing filtered results
	 */
	@GetMapping("/searchBlog")
    public String searchBlog(
    		@RequestParam("keyword") String keyword ,Model model) {
	    
		List<IndexResponseDTO> filteredBlogs = service.findFilteredBlogs(keyword);
		model.addAttribute("responseForm",filteredBlogs);
		
		return "filteredIndex :: blogList";
	}
	
	
}
