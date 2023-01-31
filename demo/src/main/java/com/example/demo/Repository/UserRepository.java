package com.example.demo.Repository;
import com.example.demo.Repository.MongoDB.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.User;

@Repository
public class UserRepository {
	@Autowired
		private UserRepositoryMongo userMongo;
	@Autowired
		private MongoOperations mongoOperations;

	public boolean addUser(String user){
			boolean result = true;
			try{
					//userMongo.save(user);
			} catch (Exception e){
					e.printStackTrace();
					result = false;
			}
			return result;
		}
}
