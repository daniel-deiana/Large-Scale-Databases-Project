package com.example.demo.Model;

import com.example.demo.DTO.ReviewDTO;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Document(collection = "users")
public class User {
		@Id
		private String id;
		@Field("username")
		private String username;
		@Field("gender")
		private String gender;
		@Field("birthday")
		private String birthday;
		@Field("hashed_password")
		private String hashed_password;
		@Field("country")
		private String country;
		@Field("reviews")
		private List<Review> mostRecentReviews = new ArrayList<>();
		@Field("token")
		private LocalDateTime token;
		@Field("admin")
		private boolean admin;

		public User(String username,String gender,String date,String country,String hashed_password){
				this.username = username;
				this.birthday = date;
				this.gender = gender;
				this.hashed_password = hashed_password;
				this.country = country;
				this.mostRecentReviews = new ArrayList<>();
		}

		public String getUsername() {
				return username;
		}
		public String getPassword() {
				return hashed_password;
		}
		public boolean isAdmin() {
			return admin;
		}
		public String getCountry() { return country;}
		public List<Review> getMostRecentReviews() { return mostRecentReviews;}
		public void setMostRecentReviews(List<Review> mostRecentReviews) { this.mostRecentReviews = mostRecentReviews; }
		public String getGender() { return gender;}
		public String getBirthday() { return birthday;}
		public List<Review>  getReview() { return mostRecentReviews;}
		public LocalDateTime getToken() { return token;}
		public void setToken(LocalDateTime now) {
			this.token = now;
		}

	public boolean getAdmin() {
			return admin;
	}
}
