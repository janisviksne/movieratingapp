package io.movie.rating.repository;

import io.movie.rating.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepo extends JpaRepository<Movie, Integer> {

    Movie findByTitleAndDescription(String title, String description);
}