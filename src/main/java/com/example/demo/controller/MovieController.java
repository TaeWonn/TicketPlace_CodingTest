package com.example.demo.controller;

import com.example.demo.dto.movie.Movie;
import com.example.demo.service.MovieService;
import com.example.demo.service.impl.MovieServiceImpl;
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

    private ObjectMapper objectMapper;

    private MovieService movieServiceImpl;

    @Autowired
    public MovieController(MovieService movieServiceImpl, ObjectMapper objectMapper) {
        this.movieServiceImpl = movieServiceImpl;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity hello() throws JsonProcessingException {
        HashMap<String, String> map = new HashMap();
        map.put("msg","Hello World");

        return  ResponseEntity.status(HttpStatus.OK)
                    .body(objectMapper.writeValueAsString(map));
    }

    @GetMapping(value = "/movie",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findMovie() throws JsonProcessingException {
        List<Movie> result = movieServiceImpl.findList();

        result.add(new Movie()); //testing code

        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(result));
    }

    @GetMapping(value = "/movie/{movie_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable("movie_id") Long id){

        Movie movie = movieServiceImpl.findById(id);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @PostMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveMovie(@ModelAttribute Movie movie){

        System.out.println("id : "+movieServiceImpl.saveAndFlush(movie));
        System.out.println("title : "+movie.getTitle());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/movie")
    public void updateMovie(@ModelAttribute Movie movie){
        movieServiceImpl.save(movie);
    }

    @DeleteMapping("/movie")
    public void deleteMovie(@RequestParam(value = "movie_id") Long id) {
        movieServiceImpl.deleteById(id);
    }

}
