package com.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.DTO.ForgotPasswordForm;
import com.blog.DTO.LoginForm;
import com.blog.DTO.RegistrationForm;
import com.blog.Service.UserService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String getIndexPage() {
		
		return "index";
	}
	
	@GetMapping("/signUp")
	public String getSignUpPage(Model model) {
		
		model.addAttribute("regForm",new RegistrationForm());
				
		return "registration";
	}
	
	@PostMapping("/signUp")
	public String signUp(@ModelAttribute("regForm") RegistrationForm regForm , Model model) {
		
		Boolean status = service.signUp(regForm);
		if(status)
		{
			model.addAttribute("succMsg","You have Created Account Successfully");
		}
		else
		{
			model.addAttribute("errMsg","Registration Failed email is already Registered");
		}		
		return "registration";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) 
	{
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model) 
	{
		String status = service.login(loginForm);
		
		if(status.equals("SUCCESS"))
		{
			//model.addAttribute("succMsg","You have Loggedin Successfully");
			return "redirect:/dashboard";
		}
		else
		{
			model.addAttribute("errMsg",status);
		}
		
		return "login";
		
	}
	
	@GetMapping("/forgot")
	public String getForgotPage(Model model) 
	{
		model.addAttribute("forgotForm", new ForgotPasswordForm());
		return "forgotPassword";
	}
	
	@PostMapping("/forgot")
	public String forgotUser(@ModelAttribute("forgotForm") ForgotPasswordForm forgotForm,Model model) 
	{
		String status = service.updatePassword(forgotForm);
		if(status.equals("SUCCESS"))
		{
			model.addAttribute("succMsg","New password created Successfully");
			
		}
		else
		{
			model.addAttribute("errMsg",status);
		}
		
		return "forgotPassword";
	}
	

	
	
}
