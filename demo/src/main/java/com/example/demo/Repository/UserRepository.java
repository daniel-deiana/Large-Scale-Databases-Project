package com.example.demo.Repository;


import com.example.demo.DTO.FigureDTO;
import com.example.demo.Model.Review;
import com.example.demo.Model.User;
import com.example.demo.Repository.MongoDB.ReviewRepositoryMongo;
import com.example.demo.Repository.MongoDB.UserRepositoryMongo;
import com.example.demo.Repository.Neo4j.UserNeo4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.neo4j.driver.Record;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class UserRepository {
	Logger logger = LoggerFactory.getLogger(UserRepository.class);
	@Autowired
	private UserRepositoryMongo userMongo;
	@Autowired
	private MongoOperations mongoOperations;
	@Autowired
	private ReviewRepositoryMongo revMongo;

	UserNeo4j neo4j = new UserNeo4j();


	public boolean addUser(User user) {
		boolean result = true;
		try {
			userMongo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	//This function update the list of the most recent reviews of a user when a new review is added
	public void updateMostReviewed(Review review) {
		try {
			Optional<User> user = userMongo.findByUsername(review.getProfile());
			List<Review> reviewList = user.get().getMostRecentReviews();
			if (reviewList.size() >= 5) {
				reviewList.remove(4);
				reviewList.add(0, review);
			} else {
				reviewList.add(0, review);
			}
			user.get().setMostRecentReviews(reviewList);
			userMongo.save(user.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean deleteUser(User user) {
		boolean result = true;
		try {
			userMongo.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public Optional<User> getUserByUsername(String username) {
		Optional<User> user = Optional.empty();
		try {
			user = userMongo.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean checkAdmin(String username) {
		Optional<User> result;
		try {
			result = getUserByUsername(username);
			if (result.isEmpty())
				return false;
			if (!result.get().isAdmin())
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//////////////////////////////// NEO4J /////////////////////////////////

	public List<FigureDTO> getTop10(String username) {
		List<FigureDTO> figures = new ArrayList<>();
		List<Record> records = neo4j.getTop10ByUsername(username);
		for (Record r : records) {
			String name = r.values().get(0).get("name").asString();
			String anime = r.values().get(0).get("anime").asString();
			String img = r.values().get(0).get("img").asString();
			FigureDTO fig = new FigureDTO(name, anime, img);
			figures.add(fig);
		}
		return figures;
	}

	public List<FigureDTO> getCharacters(String username) {
		List<FigureDTO> figures = new ArrayList<>();
		List<Record> records = neo4j.getCharacters(username);
		for (Record r : records) {
			String name = r.values().get(0).get("name").asString();
			String anime = r.values().get(0).get("anime").asString();
			String img = r.values().get(0).get("img").asString();
			FigureDTO fig = new FigureDTO(name, anime, img);
			figures.add(fig);
		}
		return figures;
	}

	public FigureDTO findCharacter(String name, String username) {
		List<Record> records = neo4j.getCharacter(name, username);
		if (records.isEmpty()) {
			return null;
		}
		return new FigureDTO(
				records.get(0).values().get(0).get("name").asString(),
				records.get(0).values().get(0).get("anime").asString(),
				records.get(0).values().get(0).get("img").asString()
		);
	}

	public boolean addToTop10(String name, String username) {
		List<Record> records = neo4j.getCharacter(name, username);
		if (records.isEmpty()) {
			return false;
		}
		return true;
	}

	public int AddToTop10(String username, String name_character) {

		// è già nella top10? errore 2
		List<FigureDTO> top10 = getTop10(username);
		for (FigureDTO r : top10) {
			String name = r.getName();
			if(name.equals(name_character))
				return 1;
		}

		//Top10 piena. Errore 10
		if(top10.size() == 10)
			return -1;

		neo4j.AddToTop10(name_character, username);
		return 0;
	}

	public int removeFromTop10(String username, String name_character) {

		List<FigureDTO> top10 = getTop10(username);
		for (FigureDTO r : top10) {
			String name = r.getName();
			if(name.equals(name_character)){
				neo4j.removeFromTop10(name_character, username);
				return 0;
			}
		}
		return 1;
	}
}
