package com.example.demo.DTO;

import com.example.demo.Model.Review;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class AnimeDTO {
    private String title;
    private String synopsis;
    private Integer episodes;
    private String img_url;
    private List<ReviewDTO> reviews;

    private List<FigureDTO> figures;


    public AnimeDTO(String title,String synopsis, int episodes, String img_url, List<FigureDTO> figures_list,  List<Review> reviews) {
        this.title = title;
        this.synopsis = synopsis;
        this.episodes = episodes;
        this.img_url = img_url;
        this.figures = figures_list;
        setFigures(figures_list, title);
        setMostRecentReviews(reviews);
    }

    public void setFigures(List<FigureDTO> characters, String anime) {
        List<FigureDTO> characterDTOList = new ArrayList<>();
        for (FigureDTO fig : characters) {
            FigureDTO characterDTO = new FigureDTO(
                    fig.getName(),
                    anime,
                    fig.getImage_url()
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
            reviewDTO.setProfile(review.getUser());
            reviewDTO.setTimestamp(review.getTimestamp());
            reviewDTO.setText(review.getText());
            reviewDTO.setScore(review.getScore());
            reviewDTOList.add(reviewDTO);
        }
        this.reviews =  reviewDTOList;
    }

    public String getTitle() {
        return title;
    }
    public List<FigureDTO> getFigures() {
        return figures;
    }


}
