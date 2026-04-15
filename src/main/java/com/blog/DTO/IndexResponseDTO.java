package com.blog.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class IndexResponseDTO {
	
	private Integer blogId;
	private String title;
	private String shortDesc;
	private LocalDate creationDate;

}
