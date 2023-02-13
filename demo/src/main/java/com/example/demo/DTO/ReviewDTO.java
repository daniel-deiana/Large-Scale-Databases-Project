package com.example.demo.DTO;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
public class ReviewDTO {
    private String id;
    private String profile;

    private String anime;
    private String text;

    private Integer score;

    private String date;

    public ReviewDTO(String id, String user, String anime_uid, String text, String date, Integer score) {
        this.id = id;
        this.profile = user;
        this.anime = anime_uid;
        this.text = text;
        this.date = date;
        this.score = score;
    }

    //for anime
    public ReviewDTO(String user, String anime, String text, Integer score) {
        this.profile = user;
        this.anime = anime;
        this.text = text;
        this.score = score;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getAnime() { return anime; }
    public void setAnime(String anime) { this.anime = anime; }
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getTimestamp() {
        return date;
    }

    public void setTimestamp(String timestamp) {
        this.date = timestamp;
    }


}