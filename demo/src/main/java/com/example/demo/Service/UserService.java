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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


	public boolean addUser(User user) {
		return userRepos.addUser(user);
	}

	public boolean addReview(Review review) {
		if (revRepos.getReviewsByUsernameAndAnime(review.getUser(), review.getAnime()))
			return false;
		revRepos.addReview(review);
		userRepos.updateMostReviewed(review);
		animeRepos.updateMostReviewed(review);
		return true;
	}


	public UserDTO getUser(String username) {
		Optional<User> result = userRepos.getUserByUsername(username);
		if (result.isEmpty())
			return null;
		return new UserDTO(result.get().getUsername(), result.get().getPassword(), result.get().getAdmin());
	}

	public boolean isAdmin(String username) {
		return userRepos.checkAdmin(username);
	}

// if type is 3, is used to open pack, if type is 10, is used for visualizing the other cards
	public List<FigureDTO> getReviewedFigures(String username, int k) {
		List<Review> reviews;
		reviews = revRepos.getReviewsByUsername(username);
		List<FigureDTO> figures = new ArrayList<>();
		for (Review rev : reviews) {
			String name_anime = rev.getAnime();
			Optional<Anime> result = animeRepos.getAnimeByTitle(name_anime);
			AnimeDTO anime = new AnimeDTO(result.get().getTitle(),
					result.get().getSynopsis(),
					result.get().getEpisodes(),
					result.get().getImg_url(),
					result.get().getFigures(),
					result.get().getReviews());
			for (FigureDTO fig : anime.getFigures()) {
				FigureDTO figure = new FigureDTO(fig.getName(),anime.getTitle(),fig.getImage_url());
				figures.add(figure);
			}
		}
		int len = figures.size();
		int i;
		if(len<k)
			k = len;
		List<FigureDTO> pack = new ArrayList<>();

		for (i = 0; i < k; i++) {
			int rand = (int) Math.floor(Math.random() * len);
			pack.add(figures.get(rand));
			figures.remove(rand);
			len = len-1;
		}
		return pack;
	}

	public UserDTO loadProfile(String username, String myself) {
		Optional<User> result = userRepos.getUserByUsername(username);
		if (result.isEmpty())
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

	public List<FigureDTO> loadCharacters(String username) {
		return userRepos.getCharacters(username);
	}

	public FigureDTO getCharacter(String name, String username) {
		return userRepos.findCharacter(name, username);
	}

	public int AddToTop10(String username, String name_character) {
		return userRepos.AddToTop10(username, name_character);
	}

	public int removeFromTop10(String username, String name_character) {
		return userRepos.removeFromTop10(username, name_character);
	}

	public void addHasCharacter(String username, List<FigureDTO> list_characters) {
		userRepos.addHasCharacter(username, list_characters);
	}

    public List<UserDTO> suggestedUsers(String myself) {
		return userRepos.getSuggestedUsers(myself);
    }

	public boolean checkToken(String myself, LocalDateTime now) {
		return userRepos.checkTokenTime(myself, now);
	}
}
