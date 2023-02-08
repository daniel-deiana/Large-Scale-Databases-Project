package com.example.demo.Service;


import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Model.User;
import com.example.demo.Repository.AnimeRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("mainUserService")
public class UserService {
		@Autowired
		UserRepository userRepos;
		@Autowired
		ReviewRepository revRepos;

		@Autowired
		AnimeRepository animeRepos;

		public boolean addUser(User user){
			return userRepos.addUser(user);
			// aggiungi neo4j
		}
		public boolean addReview(Review review){
			if (revRepos.getReviewsByUsernameAndAnime(review.getProfile(), review.getAnime()))
				return false;
			revRepos.addReview(review);
			return true;
		}


		public UserDTO getUser(String username) {
			Optional<User> result = userRepos.getUserByUsername(username);
			if (result.isEmpty())
				return null;
			return new UserDTO(result.get().getUsername(),result.get().getPassword());
			}
		public boolean isAdmin(String username) {
		return userRepos.checkAdmin(username);
	}



	public List<FigureDTO> getReviewedFigures(String username){
			List<Review> reviews;
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
			}
		return figures;
		}

	public UserDTO loadProfile(String username, String myself) {
		Optional<User> result = userRepos.getUserByUsername(username);
		if(result.isEmpty())
			return null;
		//logger.warn(Long.toString(System.currentTimeMillis()));
		// creating the DTO to show to the user
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(result.get().getUsername());
		userDTO.setPassword(result.get().getPassword());
		userDTO.setGender(result.get().getGender());
		userDTO.setBirthday(result.get().getBirthday());
		userDTO.setCountry(result.get().getCountry());
		userDTO.setMostRecentReviews(result.get().getMostRecentReviews(), username);

		//userDTO.setFollowers(userRepos.findFollowerNumberByUsername(username));
		/*
		if(myself != null){
			if(!myself.equals(username)) {
				List<InCommonGenericDTO> inCommonFollowers = userRepo.findInCommonFollowers(myself, username);
				logger.warn(Long.toString(System.currentTimeMillis()));
				List<Tournament> inCommonTournaments = tournamentRepo.getInCommonTournaments(myself, username);
				logger.warn(Long.toString(System.currentTimeMillis()));
				List<TournamentDTO> tournamentDTOList = new ArrayList<>();
				for (Tournament t : inCommonTournaments) {
					TournamentDTO tDTO = new TournamentDTO();
					tDTO.setDate(t.getDate());
					tDTO.setTournamentGame(t.getTournamentGame());
					tDTO.setClosed(t.isClosed());
					tournamentDTOList.add(tDTO);
				}
				userDTO.setInCommonFollowers(inCommonFollowers);
				userDTO.setInCommonTournaments(tournamentDTOList);
			} else {
				List<Tournament> myTournaments = tournamentRepo.getTournamentsByUser(myself);
				logger.warn(Long.toString(System.currentTimeMillis()));
				List<TournamentDTO> tournamentDTOList = new ArrayList<>();
				for (Tournament t : myTournaments) {
					TournamentDTO tDTO = new TournamentDTO();
					tDTO.setDate(t.getDate());
					tDTO.setTournamentGame(t.getTournamentGame());
					tDTO.setClosed(t.isClosed());
					tournamentDTOList.add(tDTO);
				}
				userDTO.setInCommonTournaments(tournamentDTOList);
			}

			logger.warn(Long.toString(System.currentTimeMillis()));
			userDTO.setFollowed(userRepo.isFollowed(myself, username));
			logger.warn(Long.toString(System.currentTimeMillis()));
		}*/

		return userDTO;
	}

}
