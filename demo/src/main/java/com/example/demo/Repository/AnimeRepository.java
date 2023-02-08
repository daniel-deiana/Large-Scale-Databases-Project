package com.example.demo.Repository;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Review;
import com.example.demo.Repository.MongoDB.AnimeRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
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
}
