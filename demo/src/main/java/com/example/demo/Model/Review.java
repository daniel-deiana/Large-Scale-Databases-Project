package com.example.demo.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "post")
public class Review {
    @Id
    private String id;
    private String user;
    private String anime;
    private String text;

    private Integer score;
    private String timestamp;

    public Review(String user, String anime, String text, Integer score) {
        this.user = user;
        this.anime = anime;
        this.text = text;
        this.score = score;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(Calendar.getInstance().getTime());
    }



    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(Integer score) {
        this.score = score;
    }

    public Integer getScore() {return score;}
    public void setScore(String user) {
        this.user = user;
    }
    public String getAnime() {
        return anime;
    }
    public void setGame(String anime) {
        this.anime = anime;
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