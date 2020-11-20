package com.example.demo.service.impl;

import com.example.demo.dto.movie.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findList() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public Long save(Movie movie) {
        movieRepository.saveAndFlush(movie);
        return movie.getId();
    }

    @Override
    public Long saveAndFlush(Movie movie) throws Exception {
        Movie m = movieRepository.saveAndFlush(movie);
        System.out.println("service title : "+m.getTitle());
        return movie.getId();
    }

    @Override
    public void update(Movie movie) throws Exception{
        Movie find = movieRepository.findById(movie.getId()).orElseThrow();
        find.setTitle(movie.getTitle());
        find.setDirector(movie.getDirector());
        find.setRunningTime(movie.getRunningTime());
        find.setOpenDate(movie.getOpenDate());
        find.setDistributor(movie.getDistributor());
        find.setDescription(movie.getDescription());
        find.setCumulativeAudience(movie.getCumulativeAudience());
        find.setRank(movie.getRank());
        find.setCountry(movie.getCountry());
        find.setCategory(movie.getCategory());
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
