package com.example.demo.Repository.Neo4j;

import com.example.demo.DTO.FigureDTO;
import org.neo4j.driver.Record;

import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class CharactersNeo4j {
    private final Neo4jManager neo4j;
    public CharactersNeo4j(){this.neo4j = Neo4jManager.getIstance();}



    //////////////////////////////////// MANAGING CHARACTERS  /////////////////////////////////////////////



    //This function checks if a character is already in username's Top10
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



    //////////////////////////////////// CRUD OPERATIONS  /////////////////////////////////////////////



    //This function adds a character into character collection
    public void addCharacter(FigureDTO figure) {
        try{
            neo4j.write(" CREATE (n:Character {name: $name,anime: $anime, url: $image})",
                    parameters("name", figure.getName(), "anime", figure.getAnime(), "image", figure.getImage_url())
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //This function removes a character from character collection
    public void deleteCharacter(String name) {
        try{
            neo4j.write(" MATCH (c:Character {name: $name}) DELETE c",
                    parameters("name", name)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //////////////////////////////////// CHARACTERS ANALYTICS  /////////////////////////////////////////////



    //This function returns the character with the highest number of 'AddToTop10' relationships
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

    //This function returns the character with the lowest number of 'Has' relationships
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

    //This function returns the character with the highest number of 'Has' relationships
    // and the lowest number of 'AddToTop10' relationships at the same time
    public List<Record> getMostUnusedCharacter(String how_order) {
        try{
            if (how_order.equals("DESC")){
                return neo4j.read("MATCH (u:User)-[r1:HAS]->(c:Character) \n" +
                        "WHERE NOT (c)<-[:ADDTOTOP10]-(u)\n" +
                        "RETURN DISTINCT (c.name) AS character, COUNT(r1) as Uselessness\n" +
                        "ORDER BY Uselessness ASC LIMIT 10"
                );
            }
            else{
                return neo4j.read("MATCH (u:User)-[r1:HAS]->(c:Character) \n" +
                        "WHERE NOT (c)<-[:ADDTOTOP10]-(u)\n" +
                        "RETURN DISTINCT (c.name) AS character, COUNT(r1) as Uselessness\n" +
                        "ORDER BY Uselessness DESC LIMIT 10"
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}