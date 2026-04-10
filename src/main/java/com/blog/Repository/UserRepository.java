package com.blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
  public Optional<User> findByemail(String email);
}
