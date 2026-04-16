package com.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.DTO.CommentForm;
import com.blog.Entity.Comments;
import com.blog.Service.CommentService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@Autowired
	private HttpSession session;
	
	
	/**
	 * Handles request to display all comments created by the logged-in user.
	 * Retrieves userId from session, fetches corresponding comments from service layer,
	 * and adds them to the model for rendering in the comments page.
	 *
	 * @param model the Model object used to pass data to the view
	 * @return the view name "comments" to display user's comments page
	 */
	@GetMapping("/comments")
	public String getCommentsPage(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		 
		List<Comments> commentsList = service.getAllCommnetsByUser(userId);
		 model.addAttribute("commentsList",commentsList);
		
		 //commentsList.forEach(System.out::println);
		
		return "comments";
	}
	
	
	/**
	 * Handles request to add a new comment to a blog post.
	 * Processes the submitted comment form, calls the service layer to save the comment,
	 * and sets success or error flash messages based on the operation result.
	 * Redirects back to the blog detail page after adding the comment.
	 *
	 * @param commentForm the form object containing comment details
	 * @param attribue RedirectAttributes used to pass flash messages after redirect
	 * @return redirect URL to the blog detail page with blogId
	 */
	@PostMapping("/addComment")
	public String addComment(@ModelAttribute("commentForm") CommentForm commentForm,RedirectAttributes attribue) {
		
		String status = service.addComment(commentForm);
		if(status.contains("SUCCESS"))
		{
			attribue.addFlashAttribute("succMsg","Comment Created SuccessFully");
		}
		else
		{
			attribue.addFlashAttribute("errMsg",status);
		}	
		
		return "redirect:/readBlog?blogId="+commentForm.getBlogId();
	}
	
	
	/**
	 * Handles request to delete a comment by its ID.
	 * Calls the service layer to delete the specified comment
	 * and redirects the user back to the comments page.
	 *
	 * @param commentId the ID of the comment to be deleted
	 * @return redirect URL to the comments page after deletion
	 */
	@GetMapping("/delete")
	public String getDelete(@RequestParam("commentId") Integer commentId) {
		
		service.deleteComment(commentId);
		
		return "redirect:/comments";
	}
	

}
