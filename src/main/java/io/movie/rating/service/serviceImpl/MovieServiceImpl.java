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
            return movieRepo.findById(id).get();
        } else {
            throw new Exception("LOG THIS AFTER");
        }
    }

    @Override
    public Movie addNewMovie(String title, String description, double length, float rating) throws Exception {
        Movie findMovie = movieRepo.findByTitleAndDescription(title, description);

        if (!Objects.isNull(findMovie)){
            System.out.println("LOG THAT THIS MOVIE ALREADY EXISTS");
        } else{
            Movie newMovie = new Movie(title, description, length, rating);
            movieRepo.save(newMovie);
            return newMovie;
        }
        throw new Exception("LOG THIS AFTER");
    }

    @Override
    public boolean deleteMovieByTitle(String title) {
        Movie deleteMovie = movieRepo.findByTitle(title);

        if(!Objects.isNull(deleteMovie)){
            movieRepo.delete(deleteMovie);
            return true;
        } else{
            System.out.println("LOG THAT WE DID NOT FIND THE MOVIE");
            return false;
        }
    }

    //ToDo add method that saves the movie rating

}
