package com.example.demo.Repository;
import com.example.demo.Repository.MongoDB.UserRepositoryMongo;
import com.example.demo.Repository.Neo4j.UserGraph;
import com.example.demo.Repository.Neo4j.UserRepositoryNeo4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.User;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public class UserRepository {

	@Autowired
		private UserRepositoryMongo userMongo;
	@Autowired
		private MongoOperations mongoOperations;
	@Autowired
		private UserRepositoryNeo4j userNeo4j;
	public boolean addUser(User user){
			boolean result = true;
			try{
					userMongo.save(user);
			} catch (Exception e){
					e.printStackTrace();
					result = false;
			}
			return result;
		}

	public boolean deleteUser(User user) {
		boolean result = true;
		try {
			userMongo.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public Optional<User> getUserByUsername(String username){
		Optional<User> user = Optional.empty();
		try {
			user = userMongo.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean checkAdmin(String username) {
		Optional<User> result;
		try{
			result = getUserByUsername(username);
			if(result.isEmpty())
				return false;
			if(!result.get().isAdmin())
				return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public Mono<UserGraph> addUserGraph(String username) {
		return userNeo4j.save(new UserGraph(username));
	}
/*
    public Object findFollowerNumberByUsername(String username) {
    }
    // 2. Get item by name
		 /* 
	public void getGroceryItemByName(String name) {
		System.out.println("Getting item by name: " + name);
		User user = groceryItemRepo.findItemByName(name);
		System.out.println(getItemDetails(item));
		}
*/
	}
