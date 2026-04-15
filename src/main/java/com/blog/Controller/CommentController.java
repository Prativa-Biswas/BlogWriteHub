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
import com.blog.DTO.CommentsResponse;
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
	
	@GetMapping("/comments")
	public String getCommentsPage(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		 
		List<Comments> commentsList = service.getAllCommnetsByUser(userId);
		 model.addAttribute("commentsList",commentsList);
		
		 //commentsList.forEach(System.out::println);
		
		return "comments";
	}
	
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
	
	@GetMapping("/delete")
	public String getDelete(@RequestParam("commentId") Integer commentId) {
		
		service.deleteComment(commentId);
		
		return "redirect:/comments";
	}
	

}
