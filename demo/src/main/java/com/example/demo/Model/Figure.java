package com.example.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "characters")
public class Figure {
    private String name;
    private String url;
    private String anime;

    public Figure(String name, String url, String anime){
        this.name = name;
        this.url = url;
        this.anime = anime;
    }


    public Figure(String name,String image){
        this.name = name;
        this.url = image;
    }

    public Figure(){}
    public String getCharacterName() {
        return name;
    }
    public String getAnime() {
        return anime;
    }
    public String getUrl() {
        return url;
    }
}
