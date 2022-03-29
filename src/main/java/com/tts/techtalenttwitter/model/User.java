package com.tts.techtalenttwitter.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//But we are going to store User in a database..
//and in order to wire up to the database, we have to
//annotate the user with an annotation from JPA (Java Persistence API)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class User {	
	//Label this as our primary key.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	private String email;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int active; //1 means account is enabled.
	
	@CreationTimestamp 
	private Date createdAt;	
	
	//This "User" class is going to be mapped to a database entry....
	//there is no good way to store an unlimited number of roles to
	//a table entry....
	
	@ManyToMany(cascade = CascadeType.ALL) //automatically update this 
	                                       //table when users are deleted and/or
	                                       //roles are deleted.
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"), 
	           inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}
