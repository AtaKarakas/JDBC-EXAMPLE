package com.proje.service;

import java.util.List;

import com.proje.model.User;

public interface UserService {

	User saveUser (User user);
	
	boolean saveUserProduct(int userId, int productId);
	
	User updateUser(User user);
	
	boolean removeUser(int id);
	
	User findUserById(int id);
	
	User findUserByProductId(int id);
	
	List<User> findUsers();
	
}
