package io.movie.rating.controller;

import io.movie.rating.model.Movie;
import io.movie.rating.service.service_impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static io.movie.rating.EndpointConstants.MOVIE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MovieController.class)
class MovieControllerEndpointTests {

    @MockBean
    private MovieServiceImpl movieServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    Movie testDeleteMovie = new Movie("Jackass", "Id rather not", 2.0, 10);

    @Test
    void shouldReturnViewWithAllMovies() {
        when(movieServiceImpl.selectAllMovies()).thenReturn(new ArrayList<>(List.of(testDeleteMovie)));
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.get("/show-all-movies"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.model().attributeExists(MOVIE))
                    .andExpect(MockMvcResultMatchers.view().name("show-all-movies"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldReturnViewWithAddNewMovie() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.get("/add-new-movie"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("add-new-movie"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldAttemptToDeleteExistingMovie() {
        when(movieServiceImpl.deleteMovieById(testDeleteMovie.getMovieID())).thenReturn(true);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/delete-movie/{id}", testDeleteMovie.getMovieID()))
                    .andExpect(MockMvcResultMatchers.model().attribute(MOVIE, movieServiceImpl.selectOneMovieById(testDeleteMovie.getMovieID())))
                    .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                    .andExpect(redirectedUrl("/show-all-movies"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldReturnViewWithUpdateMovieById() {
        when(movieServiceImpl.selectOneMovieById(testDeleteMovie.getMovieID())).thenReturn(testDeleteMovie);
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.get("/rate-movie/{id}", testDeleteMovie.getMovieID()))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.model().attribute(MOVIE, movieServiceImpl.selectOneMovieById(testDeleteMovie.getMovieID())))
                    .andExpect(MockMvcResultMatchers.view().name("rate-movie"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}