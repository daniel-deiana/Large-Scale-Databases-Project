package com.example.demo.Repository.MongoDB;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface ReviewRepositoryMongo extends MongoRepository<Review,String> {
    Collection<? extends ReviewDTO> findByProfile(String profile);
}
