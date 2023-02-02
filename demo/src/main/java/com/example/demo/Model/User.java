package com.example.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

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

		public User(String username,String gender,String date,String country,String hashed_password){
				this.username = username;
				this.birthday = date;
				this.gender = gender;
				this.hashed_password = hashed_password;
				this.country = country; 
		}

}
