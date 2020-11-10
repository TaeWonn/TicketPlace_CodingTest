package com.example.demo.service.impl;

import com.example.demo.dto.movie.Movie;

import java.util.List;

public interface MovieService {


    List<Movie> findList();

    Movie findById(Long id);
}
