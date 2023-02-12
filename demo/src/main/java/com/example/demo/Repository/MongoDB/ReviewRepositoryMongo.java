package com.example.demo.Repository.MongoDB;
import com.example.demo.Model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepositoryMongo extends MongoRepository<Review,String> {
    List<Review> findByUser(String profile);
    Page<Review> findByAnime(String anime, PageRequest pageable);
    int countByAnime(String anime);
    boolean existsByUserAndAnime(String profile, String anime);

}
