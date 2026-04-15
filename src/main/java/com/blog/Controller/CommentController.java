package com.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.DTO.CommentForm;
import com.blog.Service.CommentService;


@Controller
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@GetMapping("/comments")
	public String getCommentsPage() {
		
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
	

}
