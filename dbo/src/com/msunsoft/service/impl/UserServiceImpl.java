package com.msunsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msunsoft.mapper.UserMapper;
import com.msunsoft.model.User;
import com.msunsoft.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public User userlogin(User user) {
		// TODO Auto-generated method stub
		return userMapper.userLogin( user);
	}

}
