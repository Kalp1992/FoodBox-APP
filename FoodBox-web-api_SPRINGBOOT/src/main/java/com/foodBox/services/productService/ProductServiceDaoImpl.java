package com.foodBox.services.productService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodBox.Repository.ProductRepo;

import com.foodBox.model.Products;

@Service
public class ProductServiceDaoImpl  implements ProductServiceDao {
	@Autowired
	ProductRepo productRepo;
	@Override
	public Products addProduct(HashMap<String, String> addProductRequest) throws Exception {
		try {
			
			Products product = new Products();
			product.setName(addProductRequest.get("name"));
			product.setPrice(addProductRequest.get("price"));	
			product.setCategory_id(addProductRequest.get("category_id"));
			product.setDescription(addProductRequest.get("description"));
			product.setAdded_on(addProductRequest.get("added_on"));
			productRepo.save(product);
			return product;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	@Override
	public List<Products> removeProductByProductId(long productId) {
		productRepo.deleteProductById(productId);;
		return productRepo.findAll();
	}

}
