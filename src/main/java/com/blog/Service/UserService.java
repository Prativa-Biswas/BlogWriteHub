package com.blog.Service;

import java.util.List;

import com.blog.DTO.ForgotPasswordForm;
import com.blog.DTO.IndexResponseDTO;
import com.blog.DTO.LoginForm;
import com.blog.DTO.RegistrationForm;
import com.blog.Entity.Blog;

public interface UserService {
	
	public Boolean signUp(RegistrationForm form);
    public String login(LoginForm form);
    public String updatePassword(ForgotPasswordForm form);
}
