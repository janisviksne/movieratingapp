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

@Controller
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping("/show-all-movies")
    public String showAllMovies(Model model) {
        model.addAttribute("movie", movieService.selectAllMovies());
        return "show-all-movies";
    }

    @GetMapping("/add-new-movie")
    public String getAddNewMovie(Movie movie) {
        return "add-new-movie";
    }

    @PostMapping("/add-new-movie")
    public String postAddNewMovie(@Validated Movie movie, BindingResult bindingResult) throws Exception {
        if (!bindingResult.hasErrors()) {
            movieService.addNewMovie(movie.getTitle(), movie.getDescription(), movie.getLength(), movie.getRating());
            return "redirect:/show-all-movies";
        } else {
            return "add-new-movie";
        }
    }

    @GetMapping("/delete-movie/{id}")
    public String getDeleteMovie(@PathVariable("id") int id, Model model) throws Exception {
        model.addAttribute("movie", movieService.selectOneMovieById(id));
        movieService.deleteMovieById(id);
        return "redirect:/show-all-movies";
    }

    @GetMapping("/rate-movie/{id}")
    public String getRateMovie(@PathVariable("id") int id, Model model) throws Exception {
        model.addAttribute("movie", movieService.selectOneMovieById(id));
        return "rate-movie";
    }

    @PostMapping("/rate-movie/{id}")
    public String postRateMovie(@PathVariable("id") int id, Movie movie) throws Exception {
        movieService.updateMovieById(id, movie.getRating());
        return "redirect:/show-all-movies";
    }


}
