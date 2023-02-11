package com.example.demo.Repository;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Repository.MongoDB.AnimeRepositoryMongo;
import com.example.demo.Repository.Neo4j.UserNeo4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

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

	///// DA CAMBIARE QUANDO VIENE MESSO IL NOME DELL'ANIME NELLE REVIEW//////

	public Optional<Anime> getAnimeByUid(String uid) {
		Optional<Anime> anime = Optional.empty();
		try {
			anime = animeMongo.findAnimeByUid(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anime;
	}

    public List<Anime> getAllAnime() {
		/*List<Anime> animeList;
		try {
			//animeList = animeMongo.findAnimeBy();
			if (animeList.isEmpty())
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return animeList;
	}*/
		return null;
	}

	public List<ResultSetDTO> getLongAnime(String how_order) {

		ProjectionOperation projectFields = project()
				.andExpression("title").as("field1")
				.andExpression("episodes").as("field2");

		SortOperation sortOperation;
		if(how_order.equals("DESC")) {
			sortOperation = sort(Sort.by(Sort.Direction.DESC, "episodes"));
		} else {
			sortOperation = sort(Sort.by(Sort.Direction.ASC, "episodes", "episodes"));
		}

		AggregationOperation limit = Aggregation.limit(5);

		Aggregation aggregation = Aggregation.newAggregation(sortOperation, projectFields, limit);

		AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "anime", ResultSetDTO.class);

		return result.getMappedResults();
	}
}
