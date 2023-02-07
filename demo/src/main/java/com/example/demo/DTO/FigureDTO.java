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

    private boolean isMyself(){
        return isMyself;
    }

    public String getName() {
        return name;
    }

    public String getAnime(String name) {
        return anime;
    }
}
