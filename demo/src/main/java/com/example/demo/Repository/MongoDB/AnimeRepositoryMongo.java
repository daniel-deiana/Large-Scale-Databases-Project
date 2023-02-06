package com.example.demo.Repository.MongoDB;
import com.example.demo.Model.Anime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimeRepositoryMongo extends MongoRepository<Anime,String> {

    Optional<Anime> findAnimeByTitle(String title);

}
