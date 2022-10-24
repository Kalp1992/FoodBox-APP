package com.foodBox.services.productService;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foodBox.model.Products;

@Service
public interface ProductServiceDao {
	Products addProduct(HashMap<String,String> addProductRequest) throws Exception;
	List<Products> removeProductByProductId(long productId);
}
