package kurtis.chudasama.repository;

import kurtis.chudasama.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findById(int id);

    ArrayList<Rating> findByItemId(int itemId);
}
