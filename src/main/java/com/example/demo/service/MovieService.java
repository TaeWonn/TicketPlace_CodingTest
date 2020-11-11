package com.example.demo.service;

import com.example.demo.dto.movie.Movie;

import java.util.List;

public interface MovieService {


    List<Movie> findList();

    Movie findById(Long id);

    void save(Movie movie);

    void deleteById(Long id);
}
