package com.example.demo.Service;


import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Repository.AnimeRepository;
import com.example.demo.Repository.CharacterRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mainAnimeService")
public class AnimeService {

	@Autowired
	AnimeRepository animeRepos;

	@Autowired
	ReviewRepository revRepos;

	@Autowired
	CharacterRepository characterRepository;

	@Autowired
	UserRepository userRepos;

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



	public List<String> GetSuggestedAnime(String username) {
		return userRepos.GetSuggestedAnime(username);
	}

	public List<ResultSetDTO> GetAppreciatedAnime(String how_order) {
		return revRepos.GetSuggestedAnime(how_order);
	}

	public List<ResultSetDTO> getLongAnime(String how_order) {
		return animeRepos.getLongAnime(how_order);
	}

	public Page<Review> getReviews(String title_anime, int current_review) {
		return revRepos.getReviews(title_anime, current_review);
	}

	public int getCountReviews(String title_anime) {
		return revRepos.getCountReviews(title_anime);
	}


}
