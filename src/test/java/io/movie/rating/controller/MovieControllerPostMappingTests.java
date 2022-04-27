package io.movie.rating.controller;

import io.movie.rating.model.Movie;
import io.movie.rating.service.serviceImpl.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@WebMvcTest
public class MovieControllerPostMappingTests {

    @MockBean
    private MovieServiceImpl movieServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    Movie testUpdateMovie = new Movie("Jackass", "Id rather not", 2.0, 10);

    @Test
    void shouldSuccessfullyAddNewMovieAndRedirectBack() throws Exception {
            this.mockMvc.perform(MockMvcRequestBuilders.post("/add-new-movie")
                            .param("title", "Borat 3")
                            .param("description", "Oh god")
                            .param("length", "3.5"))
                    .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                    .andExpect(redirectedUrl("/show-all-movies"));
    }

    @Test
    void shouldUpdateMovieRatingWith_200StatusCode() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rate-movie/{id}", testUpdateMovie.getMovieID())
                    .param("rating", "10"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(redirectedUrl("/show-all-movies"));
    }
}
