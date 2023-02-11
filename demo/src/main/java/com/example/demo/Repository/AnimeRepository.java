package com.example.demo.Repository;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Model.Figure;
import com.example.demo.Model.Review;
import com.example.demo.Model.User;
import com.example.demo.Repository.MongoDB.AnimeRepositoryMongo;
import com.example.demo.Repository.Neo4j.CharactersNeo4j;
import com.example.demo.Repository.Neo4j.UserNeo4j;
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

    public boolean addAnime(Anime anime) {
			boolean result = true;
			try {
				if(animeMongo.findAnimeByTitle(anime.getTitle()).isEmpty())
					animeMongo.save(anime);
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
			return result;
		}

	public boolean addCharacter(Figure figure0) {
		boolean result = true;
		try {
			Optional<Anime> anime = animeMongo.findAnimeByTitle(figure0.getAnime());
			if(anime.isEmpty())
				return false;

			List<Figure> figures = anime.get().getFigures();
			Figure figure = new Figure(figure0.getCharacterName(),figure0.getUrl());
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
}

