package com.blog.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DTO.CommentForm;
import com.blog.DTO.CommentsResponse;
import com.blog.Entity.Blog;
import com.blog.Entity.Comments;
import com.blog.Entity.User;
import com.blog.Repository.BlogRepository;
import com.blog.Repository.CommentRepository;
import com.blog.Repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private BlogRepository blogRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public String addComment(CommentForm form) {
		
		// Check blog Availability
		Integer blogId = form.getBlogId();
		Optional<Blog> optionalBlog = blogRepo.findById(blogId);
		if(optionalBlog.isEmpty()) {return "Blog Not Found To comment On";}
		
		//Create Comment entity 
		Comments comment = new Comments();
		comment.setName(form.getName());
		comment.setEmail(form.getEmail());
		comment.setCommentContent(form.getCommentContent());
		comment.setBlog(optionalBlog.get());
		
		//SAVE THE Comment
		commentRepo.save(comment);
		
		return "SUCCESS";
	}

	@Override
	public List<CommentsResponse> getAllCommentsByBlogId(Integer blogId) {

		//CHECK BLOG AVAILABILTY
		Optional<Blog> optional = blogRepo.findById(blogId);
		if(optional.isEmpty()) {return null;}
		
		// FIND COMMENTS PER BLOG
		List<Comments> blogList = commentRepo.findByBlog(optional.get());
		if(blogList.isEmpty()) {return null;}
		
		List<CommentsResponse> list = blogList.stream().map(comment->{
			CommentsResponse res = new CommentsResponse();
			res.setName(comment.getName());
			res.setComment(comment.getCommentContent());
			res.setCreatedOn(comment.getCreatedOn());
			return res;
		}).collect(Collectors.toList());
				
		
		return list;
	}

	@Override
	public List<Comments> getAllCommnetsByUser(Integer userId) {
		
		//CHECK USER AVAILABILITY
              Optional<User> byId = userRepo.findById(userId);
              if (byId.isEmpty()) {               
                  return Collections.emptyList();
              } 
              
              List<Comments> commentList = commentRepo.findAllCommentsByUser(userId);
		
		
		return commentList;
	}

	@Override
	public void deleteComment(Integer id) {

		commentRepo.deleteById(id);
	}

	
}
