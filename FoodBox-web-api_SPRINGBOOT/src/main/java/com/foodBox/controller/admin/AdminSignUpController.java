package com.foodBox.controller.admin;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodBox.RequestPojo.ApiResponse;
import com.foodBox.model.Admin;

import com.foodBox.services.adminService.AdminService;

@RestController
@RequestMapping("api/signup/")
public class AdminSignUpController {
	@Autowired
	  AdminService adminservice;
	@RequestMapping("admin")
	public ResponseEntity<?> adminSignUp(@RequestBody HashMap<String,String> signupRequest) {
		try {
			//TODO validation has to add for client request
			Admin admin = adminservice.signUpAdmin(signupRequest);
			return  ResponseEntity.ok(admin);
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
}
