package com.example.demo.Model;

import com.example.demo.DTO.FigureDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "anime")
public class Anime {
		@Id
		private String id;

		@Field("title")
		private String title;
		@Field("synopsis")
		private String synopsis;

		@Field("episodes")
		private Integer episodes;

		@Field("img_url")
		private String img_url;

		@Field("aired_bool")
		private String aired_bool;
		@Field("reviews")
		private List<Review> reviews = new ArrayList<>();
		@Field("characters")
		private List<FigureDTO> figures = new ArrayList<>();

		public Anime(String title, String synopsis, int episodes,String img_url, List<FigureDTO> characters, List<Review> reviews){
				this.title = title;
				this.episodes = episodes;
				this.synopsis = synopsis;
				this.img_url = img_url;
				this.figures = characters;
				this.reviews = reviews;
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
		public List<FigureDTO> getFigures() {return figures;}
		public List<Review> getReviews() {
		return reviews;
	}

		public void setFigures(List<FigureDTO> figures) {
			this.figures = figures;
		}

		public List<Review> getMostRecentReviews() { return reviews;}

		public void setMostRecentReviews(List<Review> mostRecentReviews) { this.reviews = mostRecentReviews; }

		public int getEpisodes() {
		return episodes;
	}

}
