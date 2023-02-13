package com.example.demo.Repository;
import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Repository.MongoDB.AnimeRepositoryMongo;
import com.example.demo.Repository.Neo4j.CharactersNeo4j;
import com.example.demo.Repository.Neo4j.UserNeo4j;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class AnimeRepository {
	@Autowired
	private AnimeRepositoryMongo animeMongo;
	@Autowired
	private MongoOperations mongoOperations;
	UserNeo4j userNeo4j = new UserNeo4j();
	CharactersNeo4j charactersNeo4j = new CharactersNeo4j();

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
			if (animeMongo.findAnimeByTitle(anime.getTitle()).isEmpty()) {
				animeMongo.save(anime);
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	public boolean updateAnime(Anime new_) {
		Optional<Anime> old_;
		try {
			old_ = getAnimeByTitle(new_.getTitle());
			if (old_.isEmpty())
				return false;
			old_.get().setSynopsis(new_.getSynopsis());
			old_.get().setEpisodes(new_.getEpisodes());
			old_.get().setImage(new_.getImg_url());
			animeMongo.save(old_.get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
			userNeo4j.addCharacter(figure);

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public boolean removeCharacter(FigureDTO figure0) {
		boolean result = true;
		try {
			Optional<Anime> anime = animeMongo.findAnimeByTitle(figure0.getAnime());
			if (anime.isEmpty())
				return false;

			List<FigureDTO> figures = anime.get().getFigures();
			boolean found = false;
			for(FigureDTO fig: figures){
				if (Objects.equals(fig.getName(), figure0.getName())) {
					found = true;
					figures.remove(fig);
					break;
				}
			}
			if(!found)
				return false;
			anime.get().setFigures(figures);
			animeMongo.save(anime.get());
			userNeo4j.deleteCharacter(figure0.getName());

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

	public List<ResultSetDTO> getMostLovedCharacter(String how_order) {
		List<Record> records = charactersNeo4j.getMostLovedCharacter(how_order);
		return Utilities.RecordToResultSet(records);
	}

	public List<ResultSetDTO> getMostRareCharacter(String how_order) {
		List<Record> records = charactersNeo4j.getMostRareCharacter(how_order);
		return Utilities.RecordToResultSet(records);
	}

    public List<ResultSetDTO> getMostUnusedCharacter(String how_order) {List<Record> records = charactersNeo4j.getMostUnusedCharacter(how_order);
		return Utilities.RecordToResultSet(records);
    }
}
