package com.foodBox.controller.admin;

import java.util.HashMap;
import java.util.List;

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

import com.foodBox.JWTConfiguration.ShoppingConfiguration;
import com.foodBox.RequestPojo.ApiResponse;

import com.foodBox.model.Products;

import com.foodBox.services.productService.ProductServiceDao;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/product")
//@DeclareRoles({"ADMIN", "CUSTOMER"})
public class DeleteProductbyIdController {
	@Autowired
	ProductServiceDao productService;

	@RequestMapping("deleteProduct")
	//@RolesAllowed("ADMIN")
	//@PreAuthorize("hasRole('ADMIN')")
  	public ResponseEntity<?> deleteProductbyId(@RequestBody HashMap<String,String> removeProductRequest) {
		try {
			String keys[] = {"productId"};
			if(ShoppingConfiguration.validationWithHashMap(keys, removeProductRequest)) {
				
			}
			List<Products> obj = productService.removeProductByProductId(Long.parseLong(removeProductRequest.get("productId")));
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}		
	}}
