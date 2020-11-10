package com.example.demo.dto;

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
    @Column
    private Long id;

    private String title;

    private int runningTime;

    private LocalDate openDate;

    @OneToMany
    private List<Actor> actors = new ArrayList<>();

    @OneToOne
    private Category category;

}
