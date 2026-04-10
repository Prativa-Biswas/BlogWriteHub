package com.blog.Service;

import com.blog.DTO.LoginForm;
import com.blog.DTO.RegistrationForm;

public interface UserService {
	
	public Boolean signUp(RegistrationForm form);
    public String login(LoginForm form);
}
