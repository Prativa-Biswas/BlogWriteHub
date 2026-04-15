package com.blog.DTO;

import lombok.Data;

@Data
public class CommentForm {
	
	private Integer blogId;
	private String name;
	private String email;
	private String commentContent;

}
