package com.example.demo.Repository.Neo4j;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Model.User;
import org.neo4j.driver.Record;


import static org.neo4j.driver.Values.parameters;

public class UserNeo4j {
    private final Neo4jManager neo4j;
    public UserNeo4j(){
            this.neo4j = Neo4jManager.getIstance();
        }



    ///////////////////// MANAGING FOLLOWING ///////////////////////////////



    //This function returns the Top10 of a user
    public List<Record> getTop10ByUsername(String username){
        try{
            return neo4j.read("MATCH p=(:User{username:$username})-[r:ADDTOTOP10]->(m) RETURN m",parameters("username",username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //This function let a user follow another user
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

    //This function let a user unfollow another user
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

    //This function check if a user follow another user
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

    //This function returns a specific user followed by a user
    public List<String> findFollowingByUsername(String username){
        try{
            List<Record> record_list  = neo4j.read("MATCH (u:User)-[:FOLLOWS]->(u_following:User)" +
                            " WHERE u.username=$username" +
                            " RETURN u_following",
                    parameters("username", username));

            if (record_list.isEmpty()) return null;

            List<String> following_list = new ArrayList<>();
            for (Record following : record_list) {
                String user = following.values().get(0).get("username").asString();
                following_list.add(user);
            }
            return following_list;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //This function returns the number of follower of user
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



    ///////////////////// MANAGING CHARACTERS OWNED ///////////////////////////////



    //This function returns the Characters owned by a user
    public List<Record> getCharacters(String username){
        try{
            return neo4j.read("MATCH p=(:User{username:$username})-[r:HAS]->(m) RETURN m LIMIT 25",parameters("username",username));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //This function returns a specific Character owned by a user
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

    //This function returns a number of card owned by a user
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

    //This function adds a character into user Top10
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

    //This function removes a character from user Top10
    public void removeFromTop10(String name, String username){
        try{
            neo4j.write(" MATCH (n:User {username: $username})-[r:ADDTOTOP10]->(c:Character{name: $name})DELETE r",
                    parameters("name", name, "username", username)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //This function adds a character into user ownership
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

    public List<Record> findFollowedNumberByUsername(String username) {
        try {
            return neo4j.read("MATCH (u:User)-[:FOLLOWS]->(:User)" +
                            " WHERE u.username=$username" +
                            " RETURN count(*) as numFollowed",
                    parameters("username", username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    //////////////////////////////////// CRUD OPERATIONS  /////////////////////////////////////////////



    // This function adds a user into user collection
    public void addUser(User user) {
        try{
            neo4j.write(" CREATE (u:User {username: $username})",
                    parameters("username", user.getUsername())
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // This function removes a user from user collection
    public void deleteUser(String user) {
        try{
            neo4j.write(" MATCH (u:User {username: $username}) DELETE u",
                    parameters("username", user)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //////////////////////////////////// SUGGESTIONS  /////////////////////////////////////////////



    //This function returns users sharing same followers
    public List<Record> getSuggestedUsersByFollowed(String username) {
        try {
            return neo4j.read("MATCH(u1:User{username: $username})-[:FOLLOWS]-> (u2:User)- [:FOLLOWS]->(u3:User) " +
                            "WHERE NOT (u3)<-[:FOLLOWS]-(u1) " +
                            "RETURN DISTINCT(u3.username) AS suggestedUser LIMIT 5",
                    parameters("username", username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //This function returns users sharing some characters in their Top10 with a user
    public List<Record> getSuggestedUsersByTop10(String username) {
        try {
            return neo4j.read("MATCH(u:User{username: $username})-[r:ADDTOTOP10]-> (c:Character)<-[:ADDTOTOP10]-(commonTop10:User)" +
                            "WHERE NOT (commonTop10)<-[:FOLLOWS]-(u) " +
                            "RETURN DISTINCT(commonTop10.username) AS suggestedUser,  COUNT(r) AS CommonCharacters " +
                            "ORDER BY CommonCharacters DESC LIMIT 5",
                    parameters("username", username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //This function returns users sharing some characters in their ownership with a user
    public List<Record> getSuggestedUsersByHas(String username) {
        try {
            return neo4j.read("MATCH(u:User{username: $username})-[r:HAS]-> (c:Character)<-[:HAS]-(commonTop10:User) " +
                            "WHERE NOT (commonTop10)<-[:FOLLOWS]-(u) " +
                            "RETURN DISTINCT(commonTop10.username) AS suggestedUser,  " +
                            "COUNT(r) AS CommonCharacters " +
                            "ORDER BY CommonCharacters DESC LIMIT 5",
                    parameters("username", username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    //////////////////////////////////// USERS ANALYTICS  /////////////////////////////////////////////



    //This function returns the user with the highest number of followers
    public List<Record> getMostPopularUsers(String how_order){
        try{
            if(how_order.equals("DESC")){
                return neo4j.read("MATCH (:User)-[:FOLLOWS]->(u:User) " +
                        "RETURN u.username as username, count(*) as numFollowers " +
                        "ORDER BY numFollowers DESC LIMIT 10"
                );
            }
            else{
                return neo4j.read("MATCH (:User)-[:FOLLOWS]->(u:User) " +
                        "RETURN u.username as username, count(*) as numFollowers " +
                        "ORDER BY numFollowers ASC LIMIT 10"
                );
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}