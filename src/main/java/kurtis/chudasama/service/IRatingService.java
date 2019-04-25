package kurtis.chudasama.service;

import kurtis.chudasama.entity.Rating;

import java.util.ArrayList;

public interface IRatingService {

    Rating findById(int id);

    ArrayList<Rating> findByItemId(int itemId);
}
