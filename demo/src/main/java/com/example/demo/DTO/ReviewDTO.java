package com.example.demo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewDTO {
    private String id;
    private String user;
    private String anime;
    private String text;

    private Integer score;

    private String timestamp;

    public ReviewDTO(String id, String user, String anime, String text, String timestamp, Integer score) {
        this.id = id;
        this.user = user;
        this.anime = anime;
        this.text = text;
        this.timestamp = timestamp;
        this.score = score;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getAnime() { return anime; }
    public void setAnime(String anime) { this.anime = anime; }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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