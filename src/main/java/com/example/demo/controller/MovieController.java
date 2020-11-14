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

    @Autowired
    private ObjectMapper objectMapper;

    private final MovieServiceImpl movieServiceImpl;

    public MovieController(MovieServiceImpl movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
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

        if(result.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body(objectMapper.writeValueAsString(new Movie()));

        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(result));
    }

    @GetMapping(value = "/movie/{movie_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie findById(@PathVariable("movie_id") Long id){


        return movieServiceImpl.findById(id);
    }

    @PostMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveMovie(@RequestParam("title") String title,
                                    @RequestParam("director") String director,
                                    @RequestParam("runningTime") String runningTime){
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setRunningTime(runningTime != null?Integer.parseInt(runningTime):0);
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
