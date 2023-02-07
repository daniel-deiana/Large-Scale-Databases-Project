package com.example.demo.DTO;

import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Model.Figure;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private List<ReviewDTO> reviews;
    private List<FigureDTO> figures;


    private boolean isMyself;

    public AnimeDTO(String title, String synopsis, String img_url, List<Figure> figures_list) {
        this.title = title;
        this.synopsis = synopsis;
        this.img_url = img_url;
        setFigures(figures_list);
    }

    public void setFigures(List<Figure> characters) {
        List<FigureDTO> characterDTOList = new ArrayList<>();
        for (Figure fig : characters) {
            FigureDTO characterDTO = new FigureDTO(
                    fig.getCharacterName(),
                    fig.getAnime(),
                    fig.getGender(),
                    fig.getUrl(),
                    fig.getDescription()
            );
            characterDTOList.add(characterDTO);
        }
        this.figures =  characterDTOList;
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
    public List<FigureDTO> getCharacters() {
        return figures;
    }

}
