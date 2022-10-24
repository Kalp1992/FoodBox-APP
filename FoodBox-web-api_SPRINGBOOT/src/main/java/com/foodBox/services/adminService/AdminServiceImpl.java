package com.foodBox.services.adminService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodBox.Repository.AdminRepository;

import com.foodBox.model.Admin;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminRepository adminRepo;
	@Override
	public Admin findByMobile(String mobile) throws Exception {
		return adminRepo.findByMobile(mobile).orElseThrow(()->new Exception("Admin Not found.."));
	}

	@Override
	public Admin getAdminDetailById(long adminId) throws Exception {
		return adminRepo.findById(adminId).orElseThrow(()->new Exception("Admin Not found.."));
	}

	@Override
	public Admin signUpAdmin(HashMap<String, String> signupRequest) throws Exception {
		try {
			if(adminRepo.findByMobile(signupRequest.get("mobile")).isPresent()) {
				throw new Exception("Admin is already registered with Mobile No.");
			}
			Admin admin = new Admin();
			admin.setName(signupRequest.get("name"));
			admin.setEmail(signupRequest.get("email"));
			admin.setMobile(signupRequest.get("mobile"));
			admin.setPassword(signupRequest.get("password"));
			adminRepo.save(admin);
			return admin;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

}
