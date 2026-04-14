package com.blog.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "COMMENTS_TB")
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId; 
	
	private String name;
	private String email;
	
	@Lob
	private String commentContent;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdOn;
	
	@ManyToOne
	@JoinColumn(name = "blogId")
	private Blog blog;
}
