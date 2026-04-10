package com.blog.Service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DTO.RegistrationForm;
import com.blog.Entity.User;
import com.blog.Repository.UserRepository;
import com.blog.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
  private UserRepository userRepo;
 
 @Autowired
 private EmailUtils mailSender;
	
	@Override
	public Boolean login(RegistrationForm form) {

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

}
