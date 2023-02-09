package com.example.demo.Service;


import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Figure;
import com.example.demo.Model.Review;
import com.example.demo.Model.User;
import com.example.demo.Repository.AnimeRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.UserRepository;
import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("mainUserService")
public class UserService {
		Logger logger = LoggerFactory.getLogger(UserRepository.class);
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
			userRepos.updateMostReviewed(review);
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
			int len = figures.size();
			int i;
			List<FigureDTO> pack = new ArrayList<>();
			int num1 = -1;
			int num2 = -1;

			for(i =0; i<3; i++){
					int rand = (int) Math.floor(Math.random() * len);
					if(i==0){
						num1 = rand;
					}
					else if(i==1){
						if(rand==num1){
							i--;
							continue;
						}
						num2 = rand;
					}
					else {
						if(rand==num1 || rand == num2){
							i--;
							continue;
						}
					}

					pack.add(figures.get(rand));
			}

			return pack;
	}

	public UserDTO loadProfile(String username, String myself) {
		Optional<User> result = userRepos.getUserByUsername(username);
		if(result.isEmpty())
			return null;
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(result.get().getUsername());
		userDTO.setGender(result.get().getGender());
		userDTO.setBirthday(result.get().getBirthday());
		userDTO.setCountry(result.get().getCountry());
		userDTO.setMostRecentReviews(result.get().getMostRecentReviews(), username);
		userDTO.setFollowers(userRepos.findFollowerNumberByUsername(username));
		userDTO.setFollowedNum(userRepos.findFollowedNumberByUsername(username));
		userDTO.setCardOwned(userRepos.findCardNumberByUsername(username));
		if(myself != null)
			userDTO.setFollowed(userRepos.isFollowed(myself, username));
		return userDTO;
	}

	public List<FigureDTO> loadTop10(String username, String myself) {
		return userRepos.getTop10(username);
	}

	public boolean followUser(String current, String toFollow) {
		return userRepos.followUserByUsername(current, toFollow);
	}
	public boolean unfollowUser(String current, String toUnfollow) {
		return userRepos.unfollowUserByUsername(current, toUnfollow);
	}
}
