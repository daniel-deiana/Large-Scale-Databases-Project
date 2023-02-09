package com.example.demo.Repository.Neo4j;

import java.util.List;

import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.neo4j.driver.Values.parameters;

public class UserNeo4j {
    Logger logger = LoggerFactory.getLogger(UserNeo4j.class);
    private final Neo4jManager neo4j;

        public UserNeo4j(){
            this.neo4j = Neo4jManager.getIstance();
        }
    /*
        public GraphNeo4j getGraphNeo4j() {
            return graphNeo4j;
        }
    */
    public List<Record> getTop10ByUsername(String username){
        try{
            return neo4j.read("MATCH p=(:User{username:$username})-[r:ADDTOTOP10]->(m) RETURN m",parameters("username",username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getCharacters(String username){
        try{
            return neo4j.read("MATCH p=(:User{username:$username})-[r:HAS]->(m) RETURN m LIMIT 25",parameters("username",username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}