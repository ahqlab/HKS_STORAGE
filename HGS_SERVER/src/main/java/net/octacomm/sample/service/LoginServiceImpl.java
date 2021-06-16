package net.octacomm.sample.service;

import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.User;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


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