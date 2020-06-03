package com.proje.service.impl;

import java.util.List;

import com.proje.model.User;
import com.proje.repository.UserRepo;
import com.proje.repository.impl.UsersRepoImpl;
import com.proje.service.UserService;

public class UserServiceImpl implements UserService{

	private UserRepo userRepo = new UsersRepoImpl();

	@Override
	public User saveUser(User user) {
		
		return userRepo.saveUser(user) ;
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) {
		return userRepo.saveUserProduct(userId, productId);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.updateUser(user);
	}

	@Override
	public boolean removeUser(int id) {
		return userRepo.removeUser(id);
	}

	@Override
	public User findUserById(int id) {
		return userRepo.findUserById(id);
	}

	@Override
	public User findUserByProductId(int id) {
		return userRepo.findUserByProductId(id);
	}

	@Override
	public List<User> findUsers() {
		return userRepo.findUsers();
	}
	
}
