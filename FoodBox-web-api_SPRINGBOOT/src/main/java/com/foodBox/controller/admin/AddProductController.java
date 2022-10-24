package com.foodBox.controller.admin;

import java.util.HashMap;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodBox.RequestPojo.ApiResponse;
import com.foodBox.model.Products;

import com.foodBox.services.productService.ProductServiceDao;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/product/")
//@DeclareRoles({"ADMIN", "CUSTOMER"})
public class AddProductController {
	@Autowired
	  ProductServiceDao productservice;
	@RequestMapping("add")
	//@RolesAllowed("ADMIN")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addProduct(@RequestBody HashMap<String,String> addRequest) {
		try {
			//TODO validation has to add for client request
			Products product = productservice.addProduct(addRequest);
			return  ResponseEntity.ok(product);
		}catch(Exception e ) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
	}
}
