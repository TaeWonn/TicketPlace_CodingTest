package com.example.demo.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    @Id @GeneratedValue
    @Column(name = "ACTOR_ID")
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private Integer height;

    private Integer weight;


}
