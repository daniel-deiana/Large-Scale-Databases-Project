package com.example.demo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FigureDTO {
    private String name;
    private String anime;
    private String gender;
    private String url;
    private String description;

    private boolean isMyself;

    public FigureDTO(String name, String anime, String gender, String url, String description) {
        this.name = name;
        this.anime = anime;
        this.gender = gender;
        this.url = url;
        this.description = description;
    }

    public FigureDTO(String name,String url){this.name = name; this.url = url;}

    private boolean isMyself(){
        return isMyself;
    }

    public String getName() {
        return name;
    }

    public String getAnime(String name) {
        return anime;
    }

    public String getUrl() { return url;}
}
