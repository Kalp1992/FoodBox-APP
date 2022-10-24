package com.foodBox.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodBox.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
