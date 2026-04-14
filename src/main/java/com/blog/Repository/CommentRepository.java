package com.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.Entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer> {

}
