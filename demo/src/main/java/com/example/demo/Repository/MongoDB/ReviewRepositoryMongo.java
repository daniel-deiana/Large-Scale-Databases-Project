package com.example.demo.Repository.MongoDB;
import com.example.demo.Model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepositoryMongo extends MongoRepository<Review,String> {
    List<Review> findByProfile(String profile);
    boolean existsByProfileAndAnime(String profile, String anime);

}
