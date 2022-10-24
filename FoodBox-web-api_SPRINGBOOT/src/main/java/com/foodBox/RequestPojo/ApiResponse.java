package com.foodBox.RequestPojo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class ApiResponse {
	private String email;
    private String accessToken;
 
    public ApiResponse() { }
     
    public ApiResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
    
    
}
