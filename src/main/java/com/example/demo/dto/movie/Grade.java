package com.example.demo.dto.movie;

import com.example.demo.dto.movie.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id @GeneratedValue
    @Column(name = "GRADE_ID")
    private Long id;

    private Integer age;

    private String comment;

    private Integer scope;

    private UserType userType;
}

