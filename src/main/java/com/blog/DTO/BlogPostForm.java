package com.blog.DTO;

import lombok.Data;

@Data
public class BlogPostForm {
	
	private Integer blogId;
	private String blogTitle;
	private String shortDescription;
	private String blogContent;


}
