package com.example.demo.Repository;
import com.example.demo.Model.Anime;
import com.example.demo.Repository.MongoDB.AnimeRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AnimeRepository {
	@Autowired
		private AnimeRepositoryMongo animeMongo;
	@Autowired
		private MongoOperations mongoOperations;

	public Optional<Anime> getAnimeByTitle(String title){
		Optional<Anime> anime = Optional.empty();
		try {
			anime = animeMongo.findAnimeByTitle(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anime;
	}

}
