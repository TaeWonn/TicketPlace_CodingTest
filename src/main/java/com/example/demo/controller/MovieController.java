package com.example.demo.controller;

import com.example.demo.dto.movie.Movie;
import com.example.demo.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class MovieController {


    private MovieService movieServiceImpl;

    @Autowired
    public MovieController(MovieService movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity hello() throws JsonProcessingException {
        HashMap<String, String> map = new HashMap();
        map.put("msg","Hello World");

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @GetMapping(value = "/movie",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findMovie() throws JsonProcessingException {
        List<Movie> result = movieServiceImpl.findList();

        result.add(new Movie()); //testing code 배포시 주석

        if(result.isEmpty()) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value = "/movie/{movie_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable("movie_id") Long id){

        Movie movie = movieServiceImpl.findById(id);
        movie = new Movie(); //testing code 배포시 주석

        if(movie == null) new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @PostMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveMovie(@ModelAttribute Movie movie){
        try{
            movieServiceImpl.saveAndFlush(movie);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMovie(@ModelAttribute Movie movie){
        try {
            movieServiceImpl.update(movie);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteMovie(@RequestParam(value = "movie_id") Long id) {
        try {
            movieServiceImpl.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
