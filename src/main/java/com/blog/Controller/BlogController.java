package com.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BlogController {
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String getDashboardPage() {
		
		return "dashboard";
	}
	
	@GetMapping("/comments")
	public String getCommentsPage() {
		
		return "comments";
	}
	
	@GetMapping("/addpost")
	public String getNewPostPage() {
		
		return "addPost";
	}
	

}
