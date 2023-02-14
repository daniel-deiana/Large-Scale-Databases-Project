package com.example.demo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FigureDTO {
    private String name;
    private String anime;
    private String image_url;

    public FigureDTO(String name, String anime, String url) {
        this.name = name;
        this.anime = anime;
        this.image_url = url;
    }

// For adding character embedding
    public FigureDTO(String name, String url) {
        this.name = name;
        this.anime = anime;
        this.image_url = url;
    }

    public String getName() {
        return name;
    }

    public String getAnime() {
        return anime;
    }

    public String getImage_url() { return image_url;}

    public void setAnime(String anime) { this.anime = anime;}
    public void setName(String name) { this.name = name;}

}
