package com.example.demo.controller;

import com.example.demo.dto.Movie;
import com.example.demo.service.impl.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String hello(){
        return "Hello Ticket Place";
    }

    @GetMapping("/movie")
    public List findMovie(){
        List<Movie> result = movieService.findList();
        return result;
    }

    @GetMapping("/movie/{movie_id}")
    public Movie findById(@PathVariable("movie_id") Long id){

        return movieService.findById(id);
    }

    @PostMapping("/movie")
    public Movie findMovies(){
        return null;
    }

    @PutMapping("/movie")
    public String putMovie(@RequestParam(value = "movie_id") Long id){
        return null;
    }

    @DeleteMapping("/movie")
    public String deleteMovie(@RequestParam(value = "movie_id") Long id) {
        return null;
    }

}
