package com.example.demo.Service;


import com.example.demo.DOT.AnimeDTO;
import com.example.demo.Model.Anime;
import com.example.demo.Repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainAnimeService")
public class AnimeService {

		@Autowired
		AnimeRepository animeRepos;

		public AnimeDTO getAnime(String title) {
			Optional<Anime> result = animeRepos.getAnimeByTitle(title);
			if (result.isEmpty())
				return null;
			return new AnimeDTO(result.get().getTitle(),result.get().getSynopsis(), result.get().getImg_url());
		}

}
