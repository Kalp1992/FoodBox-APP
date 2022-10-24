package com.foodBox.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodBox.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	//Optional<User> findByMobile(String mobile);
	Optional<User> findByEmail(String email);
	Optional<User> findById(Long id);
}
