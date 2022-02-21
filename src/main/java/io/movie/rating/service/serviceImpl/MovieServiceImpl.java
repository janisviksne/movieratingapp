package io.movie.rating.service.serviceImpl;

import io.movie.rating.model.Movie;
import io.movie.rating.repository.IMovieRepo;
import io.movie.rating.service.IMovieService;
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
    public Movie selectOneMovieById(int id) throws Exception {
        if(movieRepo.existsById(id)){
            return movieRepo.findById(id).orElse(null);
        } else {
            throw new Exception("This movie cannot be found!");
        }
    }

    @Override
    public void addNewMovie(String title, String description, double length, float rating) {
        Movie findMovie = movieRepo.findByTitleAndDescription(title, description);

        if (!Objects.isNull(findMovie)){
            System.out.println("This movie already exists!");
        } else{
            Movie newMovie = new Movie(title, description, length, rating);
            movieRepo.save(newMovie);
        }
    }

    @Override
    public void deleteMovieById(int id) {
        Movie deleteMovie = movieRepo.findById(id).orElse(null);
        if(!Objects.isNull(deleteMovie)){
            movieRepo.delete(deleteMovie);
        }
    }

    @Override
    public void updateMovieById(int id, float rating) throws Exception {
        Movie updateMovie = movieRepo.findById(id).orElse(null);
        if(!Objects.isNull(updateMovie)){
            updateMovie.setRating(rating);
            movieRepo.save(updateMovie);
        } else {
            throw new Exception("Incorrect id!");
        }
    }
}
