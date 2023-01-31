package com.example.demo.Repository.MongoDB;
import com.example.demo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryMongo extends MongoRepository<User,String> {

}
