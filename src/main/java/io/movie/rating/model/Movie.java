package io.movie.rating.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @ToString
@Getter @Setter @NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MovieID")
    private int movieID;

    @Column(name = "MovieTitle")
    private String movieTitle;

    @Column(name = "MovieDescription")
    private String movieDescription;

    @Column(name = "MovieLength")
    private double movieLength;

    @Column(name = "MovieRating")
    private float movieRating;

    public Movie(String movieTitle, String movieDescription, double movieLength) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieLength = movieLength;
        this.movieRating = 0;
    }
}
