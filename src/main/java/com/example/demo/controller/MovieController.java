package com.example.demo.controller;

import com.example.demo.dto.movie.Movie;
import com.example.demo.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private ObjectMapper objectMapper;

    private final MovieService movieServiceImpl;

    public MovieController(MovieService movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
    }


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity hello() throws JsonProcessingException {
        HashMap<String, String> map = new HashMap();
        map.put("msg","Hello World");

        return  ResponseEntity.status(HttpStatus.OK)
                    .body(objectMapper.writeValueAsString(map));
    }

    @GetMapping("/movie")
    public List findMovie(@RequestParam(value = "movie_id",required = false) Long id){
        List<Movie> result = movieServiceImpl.findList();
        return result;
    }

    @GetMapping(value = "/movie/{movie_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie findById(@PathVariable("movie_id") Long id){
        return movieServiceImpl.findById(id);
    }

    @PostMapping("/movie")
    public Movie findMovies(@RequestParam("movie_id") Long id){
        return movieServiceImpl.findById(id);
    }

    @PutMapping("/movie")
    public void putMovie(@ModelAttribute Movie movie){
        movieServiceImpl.save(movie);
    }

    @DeleteMapping("/movie")
    public void deleteMovie(@RequestParam(value = "movie_id") Long id) {
        movieServiceImpl.deleteById(id);
    }

}
