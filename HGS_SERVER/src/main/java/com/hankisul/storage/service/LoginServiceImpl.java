package com.hankisul.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hankisul.storage.dao.mapper.UserMapper;
import com.hankisul.storage.domain.User;
import com.hankisul.storage.exceptions.InvalidPasswordException;
import com.hankisul.storage.exceptions.NotFoundUserException;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserMapper userMapper;

	public User login(User user) throws NotFoundUserException, InvalidPasswordException {
		if (this.userMapper.getUser(user) == null) {
			throw new NotFoundUserException();
		}
		User result = this.userMapper.getUserForAuth(user);
		if (result == null) {
			throw new InvalidPasswordException();
		}
		return result;
	}
}