package com.example.demo.Repository.MongoDB;
import com.example.demo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
}
