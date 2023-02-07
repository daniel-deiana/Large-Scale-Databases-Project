package com.example.demo.Repository.MongoDB;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryMongo extends MongoRepository<Review,String> {
    List<Review> findByProfile(String profile);
}
