package com.blog.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DTO.RegistrationForm;
import com.blog.Entity.User;
import com.blog.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
  private UserRepository userRepo;	
	
	@Override
	public Boolean login(RegistrationForm form) {

		// Validation for unique email
		String email = form.getEmail();
		User optUser = userRepo.findByemail(email);
		if(optUser != null)
		{
			return false;
		}
		
		// copy Resquest form data to enity
		User user = new User();	
		BeanUtils.copyProperties(form, user);
		
		// save entity
		userRepo.save(user);				
		return true;
	}

}
