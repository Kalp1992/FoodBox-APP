package com.foodBox.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodBox.model.Admin;
@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long>  {
	Optional<Admin> findByMobile(String mobile);
}
