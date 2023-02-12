package com.example.demo.Repository;
import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.Model.Review;
import com.example.demo.Repository.MongoDB.ReviewRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@Repository
public class ReviewRepository {


    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private ReviewRepositoryMongo revMongo;

    public void addReview(Review review){
        try{
            revMongo.save(review);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Review> getReviewsByUsername(String username) {
        List<Review> revList;
        try{
            revList = revMongo.findByUser(username);
            if(revList.isEmpty())
                return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return revList;
    }

    //This function check if a username has already made a review for that Anime
    public boolean getReviewsByUsernameAndAnime(String username, String anime) {
        return revMongo.existsByUserAndAnime(username, anime);
    }

    //This function check if a username has already made a review for that Anime
    public Page<Review> getReviews(String anime, int current_page) {
        return revMongo.findByAnime(anime, PageRequest.of(current_page, 10));
    }

    public int getCountReviews(String anime) {
        return revMongo.countByAnime(anime);
    }

    public List<ResultSetDTO> getMostReviews(String how_order, String group_by) {

        // grouping by age.
        GroupOperation groupOperation = Aggregation.group(group_by).count().as("NumberReviews");

        ProjectionOperation projectFields = project()
                .andExpression("_id").as("field1")
                .andExpression("NumberReviews").as("field2");

        SortOperation sortOperation;
        if(how_order.equals("DESC")) {
            sortOperation = sort(Sort.by(Sort.Direction.DESC,  "NumberReviews"));
        } else {
            sortOperation = sort(Sort.by(Sort.Direction.ASC, "NumberReviews"));
        }

        AggregationOperation limit = Aggregation.limit(10);

        Aggregation aggregation = Aggregation.newAggregation(groupOperation, sortOperation, limit, projectFields);

        AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "reviews", ResultSetDTO.class);

        return result.getMappedResults();
    }

    public List<ResultSetDTO> getTopReviewedAnime(String how_order, int limit_number) {

        // grouping by age.
        GroupOperation groupOperation = Aggregation.group("anime").count().as("NumberReviews").avg("score").as("AvgScore");

        // filtering same age count > 1
        ProjectionOperation projectFields = project()
                .andExpression("_id").as("field1")
                .andExpression("NumberReviews").as("field2")
                .andExpression("AvgScore").as("field3");

        SortOperation sortOperation;
        if(how_order.equals("DESC")) {
            sortOperation = sort(Sort.by(Sort.Direction.DESC, "AvgScore", "NumberReviews"));
        } else {
            sortOperation = sort(Sort.by(Sort.Direction.ASC, "AvgScore", "NumberReviews"));
        }

        AggregationOperation limit = Aggregation.limit(limit_number);

        Aggregation aggregation = Aggregation.newAggregation(groupOperation, sortOperation, limit, projectFields);

        AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "reviews", ResultSetDTO.class);

        return result.getMappedResults();
    }

}