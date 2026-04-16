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


@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	/**
	 * Loads the user registration (sign up) page with an empty form.
	 *
	 * @param model Spring Model object used to bind registration form data
	 * @return registration view page
	 */
	@GetMapping("/signUp")
	public String getSignUpPage(Model model) {
		
		model.addAttribute("regForm",new RegistrationForm());
				
		return "registration";
	}
	
	
	/**
	 * Handles user registration (sign up) and creates a new account.
	 *
	 * @param regForm form object containing user registration details
	 * @param model Spring Model object used to pass success/error messages to the view
	 * @return registration view page with status message
	 */
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
	
	/**
	 * Loads the login page with an empty login form.
	 *
	 * @param model Spring Model object used to bind login form data
	 * @return login view page
	 */
	@GetMapping("/login")
	public String getLoginPage(Model model) 
	{
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	/**
	 * Handles user login authentication.
	 *
	 * @param loginForm form object containing user login credentials
	 * @param model Spring Model object used to pass error messages to the view
	 * @return redirects to dashboard on success, otherwise returns login page with error message
	 */
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
	
	/**
	 * Loads the forgot password page with an empty form.
	 *
	 * @param model Spring Model object used to bind forgot password form data
	 * @return forgotPassword view page
	 */
	@GetMapping("/forgot")
	public String getForgotPage(Model model) 
	{
		model.addAttribute("forgotForm", new ForgotPasswordForm());
		return "forgotPassword";
	}
	
	
	/**
	 * Handles forgot password request and updates the user's password.
	 *
	 * @param forgotForm form object containing email and new password details
	 * @param model Spring Model object used to pass success/error messages to the view
	 * @return forgotPassword view page with status message
	 */
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
