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

		public User(String username,String gender,String date){
				this.username = username;
				this.birthday = date;
				this.gender = gender;
		}

}
