package kurtis.chudasama.controller;

import kurtis.chudasama.entity.Item;
import kurtis.chudasama.entity.Rating;
import kurtis.chudasama.entity.User;
import kurtis.chudasama.service.ItemService;
import kurtis.chudasama.service.RatingService;
import kurtis.chudasama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class RatingController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @GetMapping("/homepage/itempage/{id}")
    public ModelAndView itemPage(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        Item item = itemService.findById(id);

        ArrayList<Rating> ratings = ratingService.findByItemId(item.getId());

        Rating rating = new Rating();

        model.addObject("item", item);
        model.addObject("rating", rating);
        model.addObject("ratings", ratings);
        model.setViewName("item_page");

        return model;
    }

    @RequestMapping(value = {"/homepage/rating"}, method= RequestMethod.POST)
    public ModelAndView rateItem(@Valid @ModelAttribute("rating") Rating rating, BindingResult bindingResult, @RequestParam("id") int id) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        Item item = itemService.findById(id);
        ArrayList<Rating> temp_ratings = ratingService.findByItemId(item.getId());

        for (int i = 0; i < temp_ratings.size(); i++) {
            Rating ratingExists = temp_ratings.get(i);

            if (ratingExists.getUser().equals(user)) {
                bindingResult.rejectValue("id", "error.rating_exists");
                if (bindingResult.hasErrors()) {
                    model.setViewName("item_page");
                    String errorRatingExists = "";
                    model.addObject("errorRatingExists", errorRatingExists);
                    model.addObject("rating", new Rating());
                    model.addObject("ratings", temp_ratings);
                    model.addObject("item", item);
                    return model;
                }
            }
        }

        rating.setItem(item);
        rating.setUser(user);
        ratingService.saveRating(rating);

        ArrayList<Rating> ratings = ratingService.findByItemId(item.getId());

        model.setViewName("item_page");
        String ratingSuccess = "";
        model.addObject("ratingSuccess", ratingSuccess);
        model.addObject("rating", rating);
        model.addObject("ratings", ratings);
        model.addObject("item", item);

        return model;
    }
}
