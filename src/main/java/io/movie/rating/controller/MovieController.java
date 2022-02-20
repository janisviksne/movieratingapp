package io.movie.rating.controller;

import io.movie.rating.model.Movie;
import io.movie.rating.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/delete-movie")
    public String getDeleteMovie (Model model){
        model.addAttribute("movie", movieService.selectAllMovies());
        return "delete-movie";
    }

    @PostMapping("/delete-movie")
    public String postDeleteMovie(Movie movie){
        if(movieService.deleteMovieByTitle(movie.getTitle())){
            return "redirect:/show-all-movies";
        } else {
            return "error-page";
        }
    }


}
