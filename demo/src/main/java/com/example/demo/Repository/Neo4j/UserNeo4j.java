package com.example.demo.Repository.Neo4j;

import java.util.List;

import com.example.demo.DTO.FigureDTO;
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

    //Check if a user has that character
    public List<Record> getCharacter(String name, String username){
        try{
            return neo4j.read(
                    "MATCH p=(u:User{username: $username})-[r:HAS]->(c:Character{name: $name}) RETURN c ",
                    parameters("name", name, "username", username)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void AddToTop10(String name, String username){
        try{
            neo4j.write(" MATCH(u:User), (c:Character) " +
                            "WHERE u.username = $username AND c.name =  $name " +
                            "CREATE (u)-[r:ADDTOTOP10]->(c)",
                            parameters("name", name, "username", username)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeFromTop10(String name, String username){
        try{
            neo4j.write(" MATCH (n:User {username: $username})-[r:ADDTOTOP10]->(c:Character{name: $name})DELETE r",
                    parameters("name", name, "username", username)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addHasCharacter(String username, String name_character){
        try{
            neo4j.write(" MATCH(u:User), (c:Character) " +
                            "WHERE u.username = $username AND c.name =  $name " +
                            "CREATE (u)-[r:HAS]->(c)",
                    parameters("name", name_character, "username", username)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}