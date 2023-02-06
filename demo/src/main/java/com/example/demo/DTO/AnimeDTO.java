package com.example.demo.DOT;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
public class AnimeDTO {
    //private Date uid;
    private String title;
    private String synopsis;
    //private String genre;
    //private String aired;
    private String episodes;
    private String img_url;
    //private String aired_bool;
    private List<String> reviews;
    private List<String> characters;


    private boolean isMyself;

    public AnimeDTO(String title, String synopsis, String img_url) {
        this.title = title;
        this.synopsis = synopsis;
        this.img_url = img_url;
    }

    private boolean isMyself(){
        return isMyself;
    }

    public AnimeDTO(String title) {
        this.title = title;
    }

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
