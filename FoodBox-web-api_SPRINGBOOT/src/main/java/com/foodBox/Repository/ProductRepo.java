package com.foodBox.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodBox.model.Products;

@Repository
public interface ProductRepo  extends JpaRepository<Products, Long>{
	@Query("Select pro FROM Products pro WHERE pro.category_id=:cat_id")
	List<Products> getByCategoryId(@Param("cat_id")String cat_id);
	@Modifying
    @Transactional
	@Query("DELETE FROM Products pro WHERE pro.id =:product_id")
	void deleteProductById(@Param("product_id")Long product_id);
}
