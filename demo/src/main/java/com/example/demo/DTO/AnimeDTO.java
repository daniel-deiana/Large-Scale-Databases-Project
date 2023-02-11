package com.example.demo.DTO;

import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Model.Figure;
import com.example.demo.Model.Review;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class AnimeDTO {
    private String uid;
    private String title;
    private String synopsis;
    //private String genre;
    //private String aired;
    private Integer episodes;
    private String img_url;
    //private String aired_bool;
    private List<ReviewDTO> reviews;
    private List<FigureDTO> figures;


    private boolean isMyself;

    public AnimeDTO(String title,String synopsis, String img_url, List<Figure> figures_list,  List<Review> reviews) {
        this.title = title;
        this.synopsis = synopsis;
        this.img_url = img_url;
        setFigures(figures_list);
        setMostRecentReviews(reviews);
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

    public void setMostRecentReviews(List<Review> reviewList) {
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for (Review review : reviewList) {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(review.getId());
            reviewDTO.setAnime(review.getAnime());
            reviewDTO.setProfile(review.getProfile());
            reviewDTO.setTimestamp(review.getTimestamp());
            reviewDTO.setText(review.getText());
            reviewDTO.setScore(review.getScore());
            reviewDTOList.add(reviewDTO);
        }
        this.reviews =  reviewDTOList;
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

    public String getUid(){return uid;}

    public String getSynopsis() {
        return synopsis;
    }

    public String getImg_url() {
        return img_url;
    }
    public List<FigureDTO> getFigures() {
        return figures;
    }


}
