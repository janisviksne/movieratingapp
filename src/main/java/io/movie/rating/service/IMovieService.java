package io.movie.rating.service;


import io.movie.rating.model.Movie;

import java.util.ArrayList;

public interface IMovieService {

    ArrayList<Movie> selectAllMovies();
    Movie selectOneMovieById(int id) throws Exception;
    Movie addNewMovie(String title, String description, double length, float rating) throws Exception;
    boolean deleteMovieByTitle(String title);

}
