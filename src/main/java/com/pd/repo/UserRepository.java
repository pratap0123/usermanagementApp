package com.pd.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pd.binding.User;

public interface UserRepository extends JpaRepository<User, Serializable> {

	public boolean existsByEmail(String email);

	@Query("select userId from User where userPassword=:userTempPassword")
	public Integer findUserIdByPassword(char[] userTempPassword);

	@Query("select u from User u where userId=:userId")
	public User findUserById(Integer userId);
	
	@Query("select userPassword from User where email=:email")
	public char[] findUserPasswordByEmail(String email);


}
