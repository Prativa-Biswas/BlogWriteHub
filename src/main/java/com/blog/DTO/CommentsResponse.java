package com.blog.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CommentsResponse {
	
	private String name;
	private String comment;
	private LocalDate createdOn;

}
