package io.movie.rating.exceptions;

public class MovieIdNotFoundException extends RuntimeException{
    public MovieIdNotFoundException(String message) {
        super(message);
    }
}
