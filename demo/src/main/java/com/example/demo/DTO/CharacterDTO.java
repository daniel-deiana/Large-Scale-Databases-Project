package com.example.demo.DTO;

import lombok.NoArgsConstructor;
import java.util.Date;
@NoArgsConstructor
public class CharacterDTO {
    private String name;
    private String anime;
    private Date gender;
    private String url;
    private String description;

    private boolean isMyself;

    public CharacterDTO(String name) {
        this.name = name;
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
