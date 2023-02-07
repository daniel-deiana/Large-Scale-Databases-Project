package com.example.demo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewDTO {
    private String id;
    private String profile;

    private String anime_uid;
    private String text;

    private Integer score;

    private String timestamp;

    public ReviewDTO(String id, String user, String anime_uid, String text, String timestamp, Integer score) {
        this.id = id;
        this.profile = user;
        this.anime_uid = anime_uid;
        this.text = text;
        this.timestamp = timestamp;
        this.score = score;
    }

    //for anime
    public ReviewDTO(String user, String anime, String text, Integer score) {
        this.profile = user;
        this.anime_uid = anime;
        this.text = text;
        this.score = score;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getAnime() { return anime_uid; }
    public void setAnime(String anime) { this.anime_uid = anime; }
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
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}