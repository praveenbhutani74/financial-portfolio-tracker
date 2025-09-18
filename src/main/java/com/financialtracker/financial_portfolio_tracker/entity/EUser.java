package com.financialtracker.financial_portfolio_tracker.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class EUser {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;
	 	
	 	@Column(name ="email")
	 	private String email;
	 	
	 	@Column(name ="username")
	 	private String username;
	 	
	 	@Column(name ="password")
	 	private String password;
	 	
		@Column(name ="created_at")
	 	private Timestamp createdAt;
		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}

		public Timestamp getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}

		@Column(name ="updated_at")
	 	private Timestamp updatedAt;
}
