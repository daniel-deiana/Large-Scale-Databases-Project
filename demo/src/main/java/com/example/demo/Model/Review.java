package com.example.demo.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    @Field("user")
    private String user;
    @Field("text")
    private String text;
    @Field("score")
    private Integer score;
    @Field("anime")
    private String anime;
    @Field("date")
    private String date;

    public Review(String user, String anime, String text, Integer score) {
        this.user = user;
        this.anime = anime;
        this.text = text;
        this.score = score;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        formatter.format(date);
        this.date = date.toString();
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
        return date;
    }
}