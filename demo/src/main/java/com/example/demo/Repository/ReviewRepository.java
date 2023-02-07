package com.example.demo.Repository;
import com.example.demo.Model.Review;
import com.example.demo.Repository.MongoDB.ReviewRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository {


    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private ReviewRepositoryMongo revMongo;

    public List<Review> getReviewsByUsername(String username) {
        List<Review> revList;
        try{
            revList = revMongo.findByProfile(username);
            if(revList.isEmpty())
                return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return revList;
    }

}
