package com.example.demo.Repository.Neo4j;

import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class CharactersNeo4j {
    Logger logger = LoggerFactory.getLogger(CharactersNeo4j.class);
    private final Neo4jManager neo4j;

        public CharactersNeo4j(){
            this.neo4j = Neo4jManager.getIstance();
        }
    /*
        public GraphNeo4j getGraphNeo4j() {
            return graphNeo4j;
        }
    */
    public List<Record> getCharacter(String name, String username){
        try{
            //MATCH p=(u:User{username: 'baekbeans'})-[r:HAS]->(c:Character{name: 'Mizune'}) RETURN c
            return neo4j.read(
                    "MATCH p=(u:User{username: $username})-[r:HAS]->(c:Character{name: $name}) RETURN c ",
                    parameters("name", name, "username", username)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}