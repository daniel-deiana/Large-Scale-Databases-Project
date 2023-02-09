package com.example.demo.Repository;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.Repository.MongoDB.CharacterRepositoryMongo;
import com.example.demo.Repository.Neo4j.CharactersNeo4j;
import com.example.demo.Repository.Neo4j.UserNeo4j;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterRepository {
    CharactersNeo4j neo4j = new CharactersNeo4j();

    @Autowired
    private CharacterRepositoryMongo characterMongo;

    @Autowired
    private MongoOperations mongoOperations;

    //////////////////////////////// NEO4J /////////////////////////////////




}
