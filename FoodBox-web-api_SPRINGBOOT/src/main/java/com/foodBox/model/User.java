package com.foodBox.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="users")
public class User implements UserDetails{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String name,email,
	password,	created_at,
	login_token,
	type,
	address,
	is_email_verified,
	mobile;
	
	
	
	public User() {
		super();
	}
	public User(long id, String name, String email, String password, String created_at, String login_token, String type,
			String address, String is_email_verified, String mobile) {
		super();
		this.id = id;
		this.name = name;
		 
		this.email = email;
		this.password = password;
		this.created_at = created_at;
		this.login_token = login_token;
		this.type = type;
		this.address = address;
		this.is_email_verified = is_email_verified;
		this.mobile = mobile;
	}
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	 @ManyToMany
	    @JoinTable(
	        name = "users_roles",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns =  @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles = new HashSet<>();
	     
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
	 this.password = password;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getLogin_token() {
		return login_token;
	}
	public void setLogin_token(String login_token) {
		this.login_token = login_token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIs_email_verified() {
		return is_email_verified;
	}
	public void setIs_email_verified(String is_email_verified) {
		this.is_email_verified = is_email_verified;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        for (Role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	        return authorities;
	    }
	 
	 public Set<Role> getRoles() {
	        return roles;
	    }
	 
	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }
	     
	    public void addRole(Role role) {
	        this.roles.add(role);
	    }
	 
	    @Override
	    public String getUsername() {
	        return this.email;
	    }
	 
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }
	 
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }
	 
	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	 
	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
	}
	

