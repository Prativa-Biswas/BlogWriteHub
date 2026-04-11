package com.blog.DTO;

import lombok.Data;

@Data
public class ForgotPasswordForm {
	
	private String email;
	private String newPassword;
	private String confirmPassword;

}
