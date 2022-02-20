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

    @Column(name = "Title", nullable = false, length = 60)
    private String title;

    @Column(name = "Description", nullable = false, length = 200)
    private String description;

    @Column(name = "Length", nullable = false)
    private double length;

    @Column(name = "Rating")
    private float rating;

    public Movie(String title, String description, double length, float rating) {
        this.title = title;
        this.description = description;
        this.length = length;
        this.rating = 0;
    }
}
