package com.example.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "anime")
public class Anime {
		@Id
		private String id;

		//@Field("uid")
		//private String uid;
		@Field("title")
		private String title;
		@Field("synopsis")
		private String synopsis;
		@Field("aired")
		private String aired;
		@Field("episodes")
		private String episodes;
		@Field("img_url")
		private String img_url;
		@Field("aired_bool")
		private String aired_bool;
		@Field("reviews")
		private List<String> reviews;
		@Field("characters")
		private List<String> characters;

		public Anime(String title, String synopsis, String img_url){
				this.title = title;
				this.synopsis = synopsis;
				this.img_url = img_url;
		}

		public Anime(){}
		public String getTitle() {
				return title;
		}
		public String getSynopsis() {
				return synopsis;
		}
		public String getImg_url() {
		return img_url;
	}

}
