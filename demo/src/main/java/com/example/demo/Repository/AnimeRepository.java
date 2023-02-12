package com.example.demo.Repository;
import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Repository.MongoDB.AnimeRepositoryMongo;
import com.example.demo.Repository.Neo4j.UserNeo4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class AnimeRepository {
	@Autowired
	private AnimeRepositoryMongo animeMongo;
	@Autowired
	private MongoOperations mongoOperations;
	UserNeo4j neo4j = new UserNeo4j();

	public Optional<Anime> getAnimeByTitle(String title){
		Optional<Anime> anime = Optional.empty();
		try {
			anime = animeMongo.findAnimeByTitle(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anime;
	}


	//This function update the list of the most recent reviews of a user when a new review is added
	public void updateMostReviewed(Review review) {
		try {
			Optional<Anime> anime = animeMongo.findAnimeByTitle(review.getAnime());
			List<Review> reviewList = anime.get().getMostRecentReviews();
			if (reviewList.size() >= 5) {
				reviewList.remove(4);
				reviewList.add(0, review);
			} else {
				reviewList.add(0, review);
			}
			anime.get().setMostRecentReviews(reviewList);
			animeMongo.save(anime.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public boolean addAnime(Anime anime) {
			boolean result = true;
			try {
				if(animeMongo.findAnimeByTitle(anime.getTitle()).isEmpty()) {
					animeMongo.save(anime);
				}
				else{
					result = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
			return result;
		}

	public boolean addCharacter(FigureDTO figure0) {
		boolean result = true;
		try {
			Optional<Anime> anime = animeMongo.findAnimeByTitle(figure0.getAnime());
			if (anime.isEmpty())
				return false;

			List<FigureDTO> figures = anime.get().getFigures();
			FigureDTO figure = new FigureDTO(figure0.getName(),figure0.getAnime(), figure0.getImage_url());
			figures.add(figure);
			anime.get().setFigures(figures);
			animeMongo.save(anime.get());
			neo4j.addCharacter(figure);

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	public List<ResultSetDTO> getLongAnime(String how_order) {

		ProjectionOperation projectFields = project()
				.andExpression("title").as("field1")
				.andExpression("episodes").as("field2");

		SortOperation sortOperation;
		if(how_order.equals("DESC")) {
			sortOperation = sort(Sort.by(Sort.Direction.DESC, "episodes"));
		} else {
			sortOperation = sort(Sort.by(Sort.Direction.ASC,  "episodes"));
		}

		AggregationOperation limit = Aggregation.limit(5);

		AggregationOperation matchOperation = Aggregation.match(Criteria.where("episodes").ne(null));

		Aggregation aggregation = Aggregation.newAggregation(sortOperation, matchOperation, projectFields, limit);

		AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "anime", ResultSetDTO.class);

		return result.getMappedResults();
	}



}

