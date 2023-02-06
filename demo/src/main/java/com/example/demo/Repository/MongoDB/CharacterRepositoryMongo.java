package com.example.demo.Repository.MongoDB;
import com.example.demo.DTO.CharacterDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CharacterRepositoryMongo extends MongoRepository<CharacterDTO,String> {
    Optional<Character> findByName(String name);
}
