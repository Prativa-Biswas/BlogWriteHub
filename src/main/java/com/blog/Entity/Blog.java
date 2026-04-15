package com.blog.Entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BLOG_TB")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blogId;
	private String blogTitle;
	private String shortDescription;
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private String blogContent;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate creationDate;
	
	@UpdateTimestamp
	private LocalDate updationDate;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "blog",cascade = CascadeType.REMOVE)
	private List<Comments> comments;
	
}
