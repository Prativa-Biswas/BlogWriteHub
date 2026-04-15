package com.blog.Service;

import java.util.List;

import com.blog.DTO.CommentForm;
import com.blog.DTO.CommentsResponse;
import com.blog.Entity.Comments;
import com.blog.Entity.User;

public interface CommentService {

	public String addComment(CommentForm form);
	public List<CommentsResponse> getAllCommentsByBlogId(Integer blogId);
	
	public List<Comments> getAllCommnetsByUser(Integer userId);
    public void deleteComment(Integer id);
}
	
