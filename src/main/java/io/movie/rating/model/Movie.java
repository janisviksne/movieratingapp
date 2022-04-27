package io.movie.rating.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity @ToString
@Getter @Setter @NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MovieID")
    private int movieID;

    @Column(name = "Title")
    @NotNull
    @Size(min = 2, max = 60)
    @Valid
    private String title;

    @Column(name = "Description")
    @Size(max = 200)
    @NotNull
    @Valid
    private String description;

    @Column(name = "Length", nullable = false)
    @NotNull
    @Min(value = 1)
    @Valid
    private double length;

    @Column(name = "Rating")
    @Valid
    @Min(value = 0, message = "Rating cannot be lower than 0!")
    @Max(value = 10, message = "Rating cannot be higher than 10!")
    private double rating;

    public Movie(String title, String description, double length, double rating) {
        this.title = title;
        this.description = description;
        this.length = length;
        this.rating = rating;
    }
}
