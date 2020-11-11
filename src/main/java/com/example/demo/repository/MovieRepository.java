package com.example.demo.repository;

import com.example.demo.dto.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface MovieRepository extends JpaRepository<Movie,Long> {

}
