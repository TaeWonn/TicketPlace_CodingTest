package com.example.demo.dto.movie;

import com.example.demo.conf.converter.movie.GenderConverter;
import com.example.demo.conf.converter.movie.RankConverter;
import com.example.demo.dto.movie.enums.Gender;
import com.example.demo.dto.movie.enums.Rank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;

    private String title;                   //제목

    private String director;                //감독

    private int runningTime;                //상영시간

    private LocalDate openDate;             //개봉일

    private String distributor;             //배급사

    private String description;             //설명

    private Integer cumulativeAudience;     //누적 관객

    @Convert(converter = RankConverter.class)
    private Rank Rank;                      //관람 연령제한

    @Convert(converter = GenderConverter.class)
    private Gender gender;                  // 성별

    @OneToOne
    private Country country;                //국가

    @OneToMany
    private List<Actor> actors = new ArrayList<>(); //배우

    @OneToOne
    private Category category;              //장르

    @OneToMany
    private List<Grade> grades = new ArrayList<>(); //평점

}

