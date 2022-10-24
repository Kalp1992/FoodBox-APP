package com.foodBox.services.adminService;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.foodBox.model.Admin;

@Service
public interface AdminService {
	Admin findByMobile(String mobile) throws Exception;
	Admin getAdminDetailById(long adminId) throws Exception;
	Admin signUpAdmin(HashMap<String,String> signupRequest) throws Exception;
}
