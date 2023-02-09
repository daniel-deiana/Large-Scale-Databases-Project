package com.example.demo.Repository.Neo4j;

import java.util.List;
import java.util.Map;

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

    public boolean followUserByUsername(String current, String toFollow){
        boolean result = true;
        try{
            neo4j.write("MATCH (uA:User) WHERE uA.username=$current" +
                            " MATCH (uB:User) WHERE uB.username=$toFollow" +
                            " MERGE (uA)-[:FOLLOWS]->(uB)",
                    parameters("current", current, "toFollow", toFollow));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public boolean unfollowUserByUsername(String current, String toUnfollow){
        boolean result = true;
        try{
            neo4j.write("MATCH (uA:User) WHERE uA.username=$current" +
                            " MATCH (uB:User) WHERE uB.username=$toUnfollow" +
                            " MATCH (uA)-[r:FOLLOWS]->(uB)" +
                            " DELETE r",
                    parameters("current", current, "toUnfollow", toUnfollow));
        } catch (Exception e){
            e.printStackTrace();
            result=false;
        }
        return result;
    }

    public List<Record> isFollowed(String current, String toCheck){
        try {
            return neo4j.read("MATCH (u:User{username:$current})" +
                            " MATCH (ub:User{username:$toCheck})" +
                            " RETURN EXISTS((u)-[:FOLLOWS]->(ub)) as isFollowed",
                    parameters("current", current, "toCheck", toCheck));
        }catch (Exception e){
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


    public List<Record> findFollowerNumberByUsername(String username){
        try{
            return neo4j.read("MATCH (:User)-[:FOLLOWS]->(u:User)" +
                            " WHERE u.username=$username" +
                            " RETURN count(*) as numFollowers",
                    parameters("username", username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> findFollowedNumberByUsername(String username){
        try{
            return neo4j.read("MATCH (u:User)-[:FOLLOWS]->(:User)" +
                            " WHERE u.username=$username" +
                            " RETURN count(*) as numFollowed",
                    parameters("username", username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> findCardNumberByUsername(String username) {
        try {
            return neo4j.read("MATCH (u:User)-[h:HAS]->()" +
                            " WHERE u.username=$username" +
                            " RETURN count(h) as numCard",
                    parameters("username", username));
        } catch (Exception e) {
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