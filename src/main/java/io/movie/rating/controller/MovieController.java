package io.movie.rating.controller;

import io.movie.rating.model.Movie;
import io.movie.rating.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static io.movie.rating.Constants.*;


@Controller
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping("/show-all-movies")
    public String showAllMovies(Model model) {
        model.addAttribute(MOVIE, movieService.selectAllMovies());
        return SHOW_ALL_MOVIES;
    }

    @GetMapping("/add-new-movie")
    public String getAddNewMovie(Movie movie) {
        return ADD_NEW_MOVIE;
    }

    @PostMapping("/add-new-movie")
    public String postAddNewMovie(@Validated Movie movie, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            movieService.addNewMovie(movie.getTitle(), movie.getDescription(), movie.getLength(), movie.getRating());
            return REDIRECT_SHOW_ALL_MOVIES;
        } else {
            return ADD_NEW_MOVIE;
        }
    }

    @GetMapping("/delete-movie/{id}")
    public String getDeleteMovie(@PathVariable("id") int id, Model model) {
        model.addAttribute(MOVIE, movieService.selectOneMovieById(id));
        movieService.deleteMovieById(id);
        return REDIRECT_SHOW_ALL_MOVIES;
    }

    @GetMapping("/rate-movie/{id}")
    public String getRateMovie(@PathVariable("id") int id, Model model) {
        model.addAttribute(MOVIE, movieService.selectOneMovieById(id));
        return "rate-movie";
    }

    @PostMapping("/rate-movie/{id}")
    public String postRateMovie(@PathVariable("id") int id, Movie movie) {
        movieService.updateMovieById(id, movie.getRating());
        return REDIRECT_SHOW_ALL_MOVIES;
    }


}
