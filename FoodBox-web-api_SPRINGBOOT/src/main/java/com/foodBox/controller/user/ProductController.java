package com.foodBox.controller.user;

import java.util.HashMap;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodBox.model.Category;

import com.foodBox.model.Products;
import com.foodBox.services.productService.ProductServices;
//@CrossOrigin
@RestController
@RequestMapping("api/product/")
//@DeclareRoles({"ADMIN", "CUSTOMER"})
public class ProductController {
	@Autowired
	ProductServices productServices;
	
	@RequestMapping("getAll")
	//@RolesAllowed({"CUSTOMER","ADMIN"})
	//@PreAuthorize("hasRole({'CUSTOMER','ADMIN'})")
	public List<Products> getAllPRoducts(){
		return productServices.getAllProducts();
	}
	
	@RequestMapping("getAllCategory")
	//@RolesAllowed({"CUSTOMER","ADMIN"})
	//@PreAuthorize("hasRole({'CUSTOMER','ADMIN'})")
	public List<Category> getAllCategory(){
		return productServices.getAllCategory();
	}
	
	@RequestMapping("getProductsByCategory")
	//@RolesAllowed({"CUSTOMER","ADMIN"})
     //@PreAuthorize("hasRole({'CUSTOMER','ADMIN'})")
	public List<Products> getProductsByCategory(@RequestBody HashMap<String,String> request){
		String category_id = request.get("cat_id");		
		return productServices.getProductsByCategory(category_id);
	}
	
}
