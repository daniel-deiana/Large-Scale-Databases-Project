package com.example.demo.Service;


import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Repository.AnimeRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("mainAnimeService")
public class AnimeService {

	@Autowired
	AnimeRepository animeRepos;

	@Autowired
	ReviewRepository revRepos;

	@Autowired
	UserRepository userRepos;

	public AnimeDTO getAnime(String title) {
		Optional<Anime> result = animeRepos.getAnimeByTitle(title);
		return result.map(anime -> new AnimeDTO(
				anime.getTitle(),
				anime.getSynopsis(),
				anime.getEpisodes(),
				anime.getImg_url(),
				anime.getFigures(),
				anime.getReviews()
		)).orElse(null);
	}

	public List<String> GetSuggestedAnime(String username) {
		return userRepos.GetSuggestedAnime(username);
	}

	public List<ResultSetDTO> GetAppreciatedAnime(String how_order) {
		return revRepos.getTopReviewedAnime(how_order, 5);
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

    public boolean addAnime(String title, String synopsis, int episodes, String image) {
		List<FigureDTO> figures = new ArrayList<>();
		List<Review> reviews = new ArrayList<>(5);
		Anime anime = new Anime(title,synopsis,episodes,image,figures,reviews);
		return animeRepos.addAnime(anime);
    }
	public boolean updateAnime(String title, String synopsis, int episodes, String image) {
		List<FigureDTO> figures = new ArrayList<>();
		List<Review> reviews = new ArrayList<>(5);
		Anime anime = new Anime(title,synopsis,episodes,image,figures,reviews);
		return animeRepos.updateAnime(anime);
	}

	public boolean addCharacter(String name, String anime, String image) {
		FigureDTO figure = new FigureDTO(name,anime,image);
		return animeRepos.addCharacter(figure);
	}

	public boolean removeCharacter(String name, String anime, String image) {
		FigureDTO figure = new FigureDTO(name,anime,image);
		return animeRepos.removeCharacter(figure);	}

	public List<ResultSetDTO> getMostReviews(String how_order, String group_by) {
		return revRepos.getMostReviews(how_order, group_by);
	}

	public List<ResultSetDTO> getMostReviews(String how_order, String group_by, int year) {
		return revRepos.getMostReviews(how_order, group_by, year);
	}

	public List<ResultSetDTO> getTopReviewedAnime(String how_order, int number) {
		return revRepos.getTopReviewedAnime(how_order, number);
	}

	public List<ResultSetDTO> getTopReviewedAnimeWeighted(String how_order) {
		return revRepos.getTopReviewedAnimeWeighted(how_order);
	}

	public List<ResultSetDTO> getTopReviewedAnime(String how_order, int number, int year) {
		return revRepos.getTopReviewedAnime(how_order, number, year);
	}

	public List<ResultSetDTO> getMostLovedCharacter(String how_order) {
		return animeRepos.getMostLovedCharacter(how_order);
	}

	public List<ResultSetDTO> getMostRareCharacter(String how_order) {
			return animeRepos.getMostRareCharacter(how_order);
	}

	public List<ResultSetDTO> getMostUnusedCharacter(String how_order) {
		return animeRepos.getMostUnusedCharacter(how_order);
	}

}
