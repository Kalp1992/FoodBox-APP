package com.foodBox.controller.user;

import java.util.HashMap;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodBox.RequestPojo.ApiResponse;
import com.foodBox.model.User;
import com.foodBox.services.userService.UserService;
//@CrossOrigin
@RestController
@RequestMapping("api/signup/")
public class SignUpController {
	@Autowired
	  UserService userservice;
	@RequestMapping("user")
	//@RolesAllowed({"ADMIN","CUSTOMER"})
	public ResponseEntity<?> userSignUp(@RequestBody HashMap<String,String> signupRequest) {
		try {
			//TODO validation has to add for client request
			User user = userservice.signUpUser(signupRequest);
			return  ResponseEntity.ok(user);
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
}
