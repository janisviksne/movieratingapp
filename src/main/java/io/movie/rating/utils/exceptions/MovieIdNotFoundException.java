package io.movie.rating.utils.exceptions;

public class MovieIdNotFoundException extends RuntimeException{
    public MovieIdNotFoundException(String message) {
        super(message);
    }
}
