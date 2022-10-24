package com.foodBox;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.foodBox.Repository.UserRepository;
import com.foodBox.model.Role;
import com.foodBox.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired private UserRepository repo;
    
    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("nam2020");
         
        User newUser = new User("nam@codejava.net", password);
        User savedUser = repo.save(newUser);
         
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testAssignRoleToUser() {
    	Long userId = 2L;
        User user = repo.findById(userId).get();
    user.addRole(new Role(2L));
     
         
        User updatedUser = repo.save(user);
        assertThat(updatedUser.getRoles()).hasSize(1);
}}

