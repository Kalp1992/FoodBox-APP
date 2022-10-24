package com.foodBox.services.userService;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.foodBox.model.User;

@Service
public interface UserService {
	User findByEmail(String mobile) throws Exception;
	User getUserDetailById(long userId) throws Exception;
	User signUpUser(HashMap<String,String> signupRequest) throws Exception;
}
