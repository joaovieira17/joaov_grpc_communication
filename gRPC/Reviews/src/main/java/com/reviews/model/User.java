package com.reviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column
	@JsonIgnore
	private String password;

	@Column(nullable = true)
	@Email
	private String email;

	@Column(nullable = true)
	private String address;


	@JsonIgnore
	@ElementCollection
	private final Set<Role> authorities = new HashSet<>();


	public User(final String username, final String password, final String email, final String address) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	protected User() {
	}

	public User(final String username, final String password){
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Role> getRoles() {
		return authorities;
	}


	public void addAuthority(Role r) {
		authorities.add(r);
	}
}
