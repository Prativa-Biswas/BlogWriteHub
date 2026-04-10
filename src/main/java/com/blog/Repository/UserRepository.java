package com.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
  public User findByemail(String email);
}
