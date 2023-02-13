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

    public List<Record> checkIntoTop10(String name, String username){
        try{
            return neo4j.read(
                    "MATCH (u:User{username: $username})-[r:ADDTOTOP10]->(c:Character{name: $name}) RETURN c ",
                    parameters("name", name, "username", username)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getMostLovedCharacter(String how_order){
        try{
            if (how_order.equals("DESC")){
                return neo4j.read("MATCH p=()-[r:ADDTOTOP10]->(c:Character)" +
                        "RETURN  c.name as nameFig,count(r) as numDesired " +
                        "ORDER BY numDesired DESC LIMIT 10"
                );
            }
            else{
                return neo4j.read("MATCH p=()-[r:ADDTOTOP10]->(c:Character)" +
                        "RETURN  c.name as nameFig,count(r) as numDesired " +
                        "ORDER BY numDesired ASC LIMIT 10"
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getMostRareCharacter(String how_order){
        try{
            if (how_order.equals("DESC")){
                return neo4j.read("MATCH p=()-[r:HAS]->(c:Character)" +
                        "RETURN  c.name as nameFig,count(r) as numOwned " +
                        "ORDER BY numOwned DESC LIMIT 10"
                );
            }
            else{
                return neo4j.read("MATCH p=()-[r:HAS]->(c:Character)" +
                        "RETURN  c.name as nameFig,count(r) as numOwned " +
                        "ORDER BY numOwned ASC LIMIT 10"
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}