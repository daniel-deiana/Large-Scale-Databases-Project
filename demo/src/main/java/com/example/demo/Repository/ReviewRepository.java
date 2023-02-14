package com.example.demo.Repository;
import com.example.demo.DTO.AnimeDTO;
import com.example.demo.DTO.ResultSetDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Model.Review;
import com.example.demo.Repository.MongoDB.AnimeRepositoryMongo;
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

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Multiply.valueOf;
import static org.springframework.data.mongodb.core.aggregation.DateOperators.Year.year;

@Repository
public class ReviewRepository {

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private ReviewRepositoryMongo revMongo;

    @Autowired
    private AnimeRepositoryMongo animeMongo;

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

    public ReviewDTO getReviewByUsernameAndAnime(String username, String anime) {
        Review rev;
        try {
            rev = revMongo.findByUserAndAnime(username, anime);
            if (rev == null)
                return null;
            return new ReviewDTO(
                    rev.getId(),
                    rev.getUser(),
                    rev.getAnime(),
                    rev.getText(),
                    rev.getDate(),
                    rev.getScore()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //This function check if a username has already made a review for that Anime
    public Page<Review> getReviews(String anime, int current_page) {
        return revMongo.findByAnime(anime, PageRequest.of(current_page, 10));
    }

    public int getCountReviews(String anime) {
        return revMongo.countByAnime(anime);
    }

    public List<ResultSetDTO> getMostReviews(String how_order, String group_by) {

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

        Aggregation aggregation = Aggregation.newAggregation(groupOperation, sortOperation, projectFields, limit);

        AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "reviews", ResultSetDTO.class);

        return result.getMappedResults();
    }


    public List<ResultSetDTO> getMostReviews(String how_order, String group_by, int year) {

        ProjectionOperation findYear = project()
                .andExpression(group_by).as(group_by)
                .and(year(DateOperators.dateFromString("$date"))).as("year");

        MatchOperation matchYear = match(new Criteria("year").is(year));

        GroupOperation groupOperation = group(group_by, "year").count().as("NumberReviews");

        ProjectionOperation projectFields = project()
                .andExpression(group_by).as("field1")
                .andExpression("NumberReviews").as("field2");

        SortOperation sortOperation;
        if(how_order.equals("DESC")) {
            sortOperation = sort(Sort.by(Sort.Direction.DESC,  "NumberReviews"));
        } else {
            sortOperation = sort(Sort.by(Sort.Direction.ASC, "NumberReviews"));
        }

        AggregationOperation limit = limit(10);

        Aggregation aggregation = newAggregation(findYear, matchYear, groupOperation, sortOperation, projectFields, limit);

        AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "reviews", ResultSetDTO.class);

        return result.getMappedResults();
    }

    public List<ResultSetDTO> getTopReviewedAnime(String how_order, int limit_number) {

        GroupOperation groupOperation = Aggregation.group("anime").count().as("NumberReviews").avg("score").as("AvgScore");

        ProjectionOperation projectFields = project()
                .andExpression("_id").as("field1")
                .andExpression("AvgScore").as("field2");

        SortOperation sortOperation;
        if(how_order.equals("DESC")) {
            sortOperation = sort(Sort.by(Sort.Direction.DESC, "AvgScore", "NumberReviews"));
        } else {
            sortOperation = sort(Sort.by(Sort.Direction.ASC, "AvgScore", "NumberReviews"));
        }

        AggregationOperation limit = Aggregation.limit(limit_number);

        Aggregation aggregation = Aggregation.newAggregation(groupOperation, sortOperation, projectFields, limit);

        AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "reviews", ResultSetDTO.class);

        return result.getMappedResults();
    }

    public List<ResultSetDTO> getTopReviewedAnime(String how_order, int limit_number, int year) {

        ProjectionOperation findYear = project()
                .andExpression("anime").as("anime")
                .andExpression("score").as("score")
                .and(year(DateOperators.dateFromString("$date"))).as("year");

        MatchOperation matchYear = match(new Criteria("year").is(year));

        GroupOperation groupOperation = Aggregation.group("anime", "year").count().as("NumberReviews").avg("score").as("AvgScore");

        ProjectionOperation projectFields = project()
                .andExpression("anime").as("field1")
                .andExpression("AvgScore").as("field2");

        SortOperation sortOperation;
        if(how_order.equals("DESC")) {
            sortOperation = sort(Sort.by(Sort.Direction.DESC, "AvgScore", "NumberReviews"));
        } else {
            sortOperation = sort(Sort.by(Sort.Direction.ASC, "AvgScore", "NumberReviews"));
        }

        AggregationOperation limit = Aggregation.limit(limit_number);

        Aggregation aggregation = Aggregation.newAggregation(findYear, matchYear, groupOperation, sortOperation, projectFields,  limit);

        AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "reviews", ResultSetDTO.class);

        return result.getMappedResults();
    }


    public List<ResultSetDTO> getTopReviewedAnimeWeighted(String how_order) {

        int num_tot_anime = (int) animeMongo.count();
        int num_tot_rew = (int) revMongo.count();
        double avg_rew = num_tot_rew/num_tot_anime;

        GroupOperation groupOperation = Aggregation.group("anime").count().as("NumberReviews").avg("score").as("AvgScore");

        ProjectionOperation projectFields = project()
                .andExpression("_id").as("field1")
                .and((valueOf("NumberReviews").multiplyBy(valueOf("AvgScore")).multiplyBy(valueOf(1/avg_rew)))).as("field2");

        SortOperation sortOperation;
        if(how_order.equals("DESC")) {
            sortOperation = sort(Sort.by(Sort.Direction.DESC, "field2"));
        } else {
            sortOperation = sort(Sort.by(Sort.Direction.ASC, "field2"));
        }

        AggregationOperation limit = Aggregation.limit(10);

        Aggregation aggregation = Aggregation.newAggregation(groupOperation, projectFields,  sortOperation, limit);

        AggregationResults<ResultSetDTO> result = mongoOperations.aggregate(aggregation, "reviews", ResultSetDTO.class);

        return result.getMappedResults();
    }

}