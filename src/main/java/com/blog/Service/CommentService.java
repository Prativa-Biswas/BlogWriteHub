package com.blog.Service;

import java.util.List;

import com.blog.DTO.CommentForm;
import com.blog.DTO.CommentsResponse;

public interface CommentService {

	public String addComment(CommentForm form);
	public List<CommentsResponse> getAllCommentsByBlogId(Integer blogId);
}
