package com.example.demo.Service;


import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Repository.AnimeRepository;
import com.example.demo.Repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mainAnimeService")
public class AnimeService {

	@Autowired
	AnimeRepository animeRepos;

	@Autowired
	AnimeRepository revRepos;

	@Autowired
	CharacterRepository characterRepository;

		public AnimeDTO getAnime(String title) {
			Optional<Anime> result = animeRepos.getAnimeByTitle(title);
			if (result.isEmpty())
				return null;
			return new AnimeDTO(
					result.get().getTitle(),
					result.get().getSynopsis(),
					result.get().getImg_url(),
					result.get().getFigures(),
					result.get().getReviews()
			);
		}

    public List<AnimeDTO> getTopAnime() {
		/*List<Review> reviews;
		reviews = revRepos.getReviewsByUsername(username);
		List<FigureDTO> figures = new ArrayList<>();
		for(Review rev: reviews){
			String name_anime =  rev.getAnime();
			Optional<Anime> result = animeRepos.getAnimeByUid(name_anime);
			AnimeDTO anime = new AnimeDTO(result.get().getTitle(),
					result.get().getSynopsis(),
					result.get().getImg_url(),
					result.get().getFigures(),
					result.get().getReviews());
			for(FigureDTO fig: anime.getFigures()) {
				FigureDTO figure = new FigureDTO(fig.getName(),fig.getUrl());
				figures.add(figure);
			}
		}*/
		return null;
    }


	/////***** NEO4J *****/////




}
