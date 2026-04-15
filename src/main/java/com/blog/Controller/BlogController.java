package com.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.DTO.BlogPostForm;
import com.blog.DTO.BlogResponse;
import com.blog.Entity.Blog;
import com.blog.Service.BlogService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class BlogController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BlogService service;
	
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String getDashboardPage(Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		List<Blog> allBlog = service.getAllBlog(userId);
	    model.addAttribute("listOfBlogs", allBlog);
		return "dashboard";
	}
	
	
	@GetMapping("/addpost")
	public String getNewPostPage(Model model) {
		
		model.addAttribute("addPostForm", new BlogPostForm());
		
		return "addPost";
	}
	
	@PostMapping("/addpost")
	public String newPostAddition(@ModelAttribute("addPostForm") BlogPostForm  addPostForm,Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		boolean status = service.addNewBlog(addPostForm, userId);
		if(status)
		{
			model.addAttribute("succMsg","Blog Created SuccessFully");
		}
		else
		{
			model.addAttribute("errMsg","User Not Found or else contentis Missing  ");
		}
		
		return "addPost";
	}
	
	@PostMapping("/deleteBlog")
	public String deleteBlog(@RequestParam("blogId") Integer blogId) {
		 System.out.println("Deleting blogId: " + blogId);
		 service.delteBlogById(blogId);
	    return "redirect:/dashboard";
	};
	
	@GetMapping("/editBlog")
	public String editBlogPage(@RequestParam("blogId") Integer blogId, Model model) {
		 Integer userId = (Integer) session.getAttribute("userId");
		 BlogPostForm editForm = service.getBlog(userId, blogId);	
		model.addAttribute("editForm",editForm);
	
	    return "editBlog";
	};
	
	@PostMapping("/editBlog")
	public String editBlog(@ModelAttribute("editForm")BlogPostForm editForm, Model model) {
		 Integer userId = (Integer) session.getAttribute("userId");
		 String status = service.updateBlog(userId, editForm);
		 
		 if(status.contains("SUCCESS"))
			{
				model.addAttribute("succMsg","Blog Updated SuccessFully");
			}
			else
			{
				model.addAttribute("errMsg", status);
			}		
	
	    return "editBlog";
	};
 
	@GetMapping("/readBlog")
	public String getBlog(@RequestParam("blogId") Integer blogId ,Model model) {
		
		BlogResponse responseBody = service.getBlogById(blogId);
		System.out.println("CONTENT DB: " + responseBody.getContent());

		model.addAttribute("responseBody",responseBody);
		
		return "viewBlog";
	}
}
  


