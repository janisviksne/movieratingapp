package io.movie.rating.repository;

import io.movie.rating.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface IMovieRepo extends CrudRepository<Movie, Integer> {

    Movie findByTitleAndDescription(String title, String description);
}