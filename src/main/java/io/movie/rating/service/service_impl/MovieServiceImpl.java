package io.movie.rating.service.service_impl;

import io.movie.rating.model.Movie;
import io.movie.rating.repository.IMovieRepo;
import io.movie.rating.service.IMovieService;
import io.movie.rating.exceptions.MovieAlreadyExistsException;
import io.movie.rating.exceptions.MovieIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Objects;


@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private IMovieRepo movieRepo;

    @Override
    public ArrayList<Movie> selectAllMovies() {
        return (ArrayList<Movie>) movieRepo.findAll();
    }

    @Override
    public Movie selectOneMovieById(int id) {
        if(movieRepo.existsById(id)){
            return movieRepo.findById(id).orElse(null);
        } else {
            throw new MovieIdNotFoundException("Incorrect id!");
        }
    }

    @Override
    public Movie addNewMovie(String title, String description, double length, double rating) {
        Movie findMovie = movieRepo.findByTitleAndDescription(title, description);

        if (Objects.nonNull(findMovie)){
            throw new MovieAlreadyExistsException("Such movie already exists!");
        } else{
            Movie newMovie = new Movie(title, description, length, rating);
            movieRepo.save(newMovie);
            return newMovie;
        }
    }

    @Override
    public boolean deleteMovieById(int id) {
        Movie deleteMovie = movieRepo.findById(id).orElse(null);
        if(!Objects.isNull(deleteMovie)){
            movieRepo.delete(deleteMovie);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void updateMovieById(int id, double rating) {
        Movie updateMovie = movieRepo.findById(id).orElse(null);
        if(Objects.nonNull(updateMovie)){
            updateMovie.setRating(rating);
            movieRepo.save(updateMovie);
        } else {
            throw new MovieIdNotFoundException("Incorrect id!");
        }
    }
}
