package kurtis.chudasama.service;

import kurtis.chudasama.entity.Rating;
import kurtis.chudasama.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("ratingService")
public class RatingService implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating findById(int id) {
        return ratingRepository.findById(id);
    }

    @Override
    public ArrayList<Rating> findByItemId(int itemId) {
        return ratingRepository.findByItemId(itemId);
    }

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
