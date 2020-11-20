package com.example.demo.service;

import com.example.demo.dto.movie.Movie;

import java.util.List;

public interface MovieService {


    List<Movie> findList();

    Movie findById(Long id);

    Long save(Movie movie);

    void deleteById(Long id) throws Exception;

    Long saveAndFlush(Movie movie) throws Exception;

    void update(Movie movie) throws Exception;
}
