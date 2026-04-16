package com.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.DTO.BlogPostForm;
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
	
	
	/**
	 * Handles user logout functionality.
	 * Invalidates the current session to clear all user data
	 * and redirects the user to the home page.
	 *
	 * @return redirect URL to the home page after logout
	 */
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	/**
	 * Handles request to display the user dashboard page.
	 * Retrieves the logged-in user's blogs using session userId,
	 * adds them to the model, and renders the dashboard view.
	 *
	 * @param model the Model object used to pass data to the view
	 * @return the view name "dashboard" to display user blogs
	 */
	@GetMapping("/dashboard")
	public String getDashboardPage(Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		List<Blog> allBlog = service.getAllBlog(userId);
	    model.addAttribute("listOfBlogs", allBlog);
		return "dashboard";
	}
	
	/**
	 * Handles request to display the "Add New Post" page.
	 * Initializes an empty BlogPostForm object and adds it to the model
	 * for form data binding in the view.
	 *
	 * @param model the Model object used to pass data to the view
	 * @return the view name "addPost" to render the new post form page
	 */
	@GetMapping("/addpost")
	public String getNewPostPage(Model model) {
		
		model.addAttribute("addPostForm", new BlogPostForm());
		
		return "addPost";
	}
	
	/**
	 * Handles submission of a new blog post.
	 * Retrieves logged-in userId from session and calls service layer
	 * to create a new blog entry. Adds success or error messages to the model
	 * based on the operation result.
	 *
	 * @param addPostForm the form object containing blog post details
	 * @param model the Model object used to pass data and messages to the view
	 * @return the view name "addPost" to show the form again with status message
	 */
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
	
	
	/**
	 * Deletes a blog post by its ID.
	 *
	 * @param blogId the ID of the blog to be deleted
	 * @return redirects to the dashboard page after deletion
	 */
	@PostMapping("/deleteBlog")
	public String deleteBlog(@RequestParam("blogId") Integer blogId) {
		 System.out.println("Deleting blogId: " + blogId);
		 service.delteBlogById(blogId);
	    return "redirect:/dashboard";
	};
	
	/**
	 * Loads the edit blog page for a specific blog.
	 *
	 * @param blogId ID of the blog to be edited
	 * @param model Spring Model object used to pass data to the view
	 * @return editBlog view page with pre-filled blog data
	 */
	@GetMapping("/editBlog")
	public String editBlogPage(@RequestParam("blogId") Integer blogId, Model model) {
		 Integer userId = (Integer) session.getAttribute("userId");
		 BlogPostForm editForm = service.getBlog(userId, blogId);	
		model.addAttribute("editForm",editForm);
	
	    return "editBlog";
	};
	
	/**
	 * Updates an existing blog post for the logged-in user.
	 *
	 * @param editForm form object containing updated blog details
	 * @param model Spring Model object used to pass success/error messages to the view
	 * @return editBlog page view with update status message
	 */
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
	
	
	/**
	 * Searches blogs created by the logged-in user based on a keyword.
	 *
	 * @param keyword search text used to filter user blogs
	 * @param model Spring Model object used to pass filtered blog list to view
	 * @return userFilteredBlogs view containing filtered results
	 */
	@GetMapping("/searchBlogByUser")
	public String findUserFinteredBlog(@RequestParam("keyword") String Keyword, Model model) {

      Integer userId = (Integer) session.getAttribute("userId");
      List<Blog> blogs = service.getUserFilteredBlogs(Keyword, userId);
	 model.addAttribute("listOfBlogs",blogs);
	
	    return "userFilteredBlogs";
	}
	
}
  


