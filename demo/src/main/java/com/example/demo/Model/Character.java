package com.example.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "users")
public class Character {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("gender")
    private String gender;
    @Field("Description")
    private String description;
    @Field("url")
    private String url;
    @Field("anime")
    private String anime;
    @Field("Love Rank")
    private String loveRank;
    @Field("ID")
    private String ID;
    @Field("Hair Color")
    private String hairColor;
    @Field("Hate Rank")
    private String hateRank;

    public Character(String name,String gender,String description,String url,String anime){
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.url = url;
        this.anime = anime;
    }

    public Character(){}
    public String getCharacterName() {
        return name;
    }
    public String getAnimeByCharacterName(String name) {
        return anime;
    }
}
