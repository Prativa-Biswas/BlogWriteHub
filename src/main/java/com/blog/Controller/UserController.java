package com.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.DTO.RegistrationForm;
import com.blog.Service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String getIndexPage() {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		
		model.addAttribute("regForm",new RegistrationForm());
				
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("regForm") RegistrationForm regForm , Model model) {
		
		Boolean status = service.login(regForm);
		if(status)
		{
			model.addAttribute("succMsg","You have Created Account Successfully");
		}
		else
		{
			model.addAttribute("errMsg","Registration Failed email is already Registered");

		}		
		return "login";
	}
	
}
