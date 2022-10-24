package com.foodBox.controller.user;

import java.util.HashMap;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.foodBox.JWTConfiguration.JwtTokenUtil;

import com.foodBox.RequestPojo.ApiResponse;
import com.foodBox.RequestPojo.LoginRequest;
import com.foodBox.model.User;
//@CrossOrigin
@RestController
@RequestMapping("api")
//@DeclareRoles({"ADMIN", "CUSTOMER"})
public class LoginController {
	 @Autowired AuthenticationManager authManager;
	    @Autowired JwtTokenUtil jwtUtil;
	    
	    @RequestMapping("/login/user")
	    //@RolesAllowed({"ADMIN","CUSTOMER"})
	    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	        try {
	            Authentication authentication = authManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            request.getEmail(), request.getPassword())
	            );
	             
	            User user = (User) authentication.getPrincipal();
	            String accessToken = jwtUtil.generateAccessToken(user);
	            ApiResponse response = new ApiResponse(user.getEmail(), accessToken);
	             
	            return ResponseEntity.ok().body(response);
	             
	        } catch (BadCredentialsException ex) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }
	    }
}
