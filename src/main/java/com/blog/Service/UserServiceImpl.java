package com.blog.Service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DTO.ForgotPasswordForm;
import com.blog.DTO.LoginForm;
import com.blog.DTO.RegistrationForm;
import com.blog.Entity.User;
import com.blog.Repository.UserRepository;
import com.blog.utils.EmailUtils;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
  private UserRepository userRepo;
 
 @Autowired
 private EmailUtils mailSender;
 
 @Autowired
 private HttpSession session;
	
	@Override
	public Boolean signUp(RegistrationForm form) {

		// Validation for unique email
		String email = form.getEmail();
		Optional<User> optUser = userRepo.findByemail(email);
		if(optUser.isPresent())
		{
			return false;
		}
		
		// copy Resquest form data to enity
		User user = new User();	
		BeanUtils.copyProperties(form, user);
		
		// save entity
		userRepo.save(user);
		
		//send Email Success message 
		String subject = "Account creation ";
		StringBuffer body = new StringBuffer();
		body.append("Dear " + form.getFirstName() + ",");
		body.append("<br><br>");
		body.append("Your BlogHub account has been created successfully.");
		body.append("<br>");
		body.append("You can now login and start sharing your blogs.");
		body.append("<br><br>");
		body.append("Happy Blogging! 😊");
		body.append("<br><br>");
		body.append("Regards,");
		body.append("<br>");
		body.append("BlogHub Team");

       boolean mailStatus = mailSender.sendMail(email, subject, body.toString());	
       if(mailStatus ==false)
       {
    	   return false;
       }
       
		return true;
	}

	@Override
	public String login(LoginForm form) {
		
		Optional<User> optionalEmail = userRepo.findByemail(form.getEmail());
		
		//email check
		if(optionalEmail.isEmpty()) {return "User Not found for "+form.getEmail(); }
		
		// password check 
		if(!form.getPassword().equals(optionalEmail.get().getPassword())) 
		{
			return "Invalid Credential";
		}
		
		session.setAttribute("userId", optionalEmail.get().getUserId());

		return "SUCCESS";
	}

	@Override
	public String updatePassword(ForgotPasswordForm form) {

		String email = form.getEmail();
     Optional<User> optionalEmail = userRepo.findByemail(email);
		
		//User Availability check
		if(optionalEmail.isEmpty()) {return "User Not found for "+email+" Please  Complete Registration " ; }
		
	    // Password Mismatch check
		if(!form.getNewPassword().equals(form.getConfirmPassword()))
		{
			return "Password Missmatch";
		}
		
		User user = optionalEmail.get();
		user.setPassword(form.getNewPassword());
		userRepo.save(user);
		
		//send Email Success message 
				String subject = "Password Updation ";
				StringBuffer body = new StringBuffer();
				body.append("Dear " + user.getFirstName() + ",");
				body.append("<br><br>");
				body.append("Your BlogHub account Password has been Changed successfully.");
				body.append("<br>");
				body.append("You can now login with your new Password and start sharing your blogs.");
				body.append("<br><br>");
				body.append("Happy Blogging! 😊");
				body.append("<br><br>");
				body.append("Regards,");
				body.append("<br>");
				body.append("BlogHub Team");

		       mailSender.sendMail(email, subject, body.toString());	
		
		return "SUCCESS";
	}

}
