package com.example.demo.Repository;
import com.example.demo.DTO.FigureDTO;
import com.example.demo.Repository.MongoDB.CharacterRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterRepository {

    @Autowired
    private CharacterRepositoryMongo characterMongo;

    @Autowired
    private MongoOperations mongoOperations;


}
