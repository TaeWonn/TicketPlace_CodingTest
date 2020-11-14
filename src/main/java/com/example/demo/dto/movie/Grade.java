package com.example.demo.dto.movie;


import com.example.demo.conf.converter.movie.GenderConverter;
import com.example.demo.conf.converter.movie.UserTypeConverter;
import com.example.demo.dto.movie.enums.Gender;
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

    @Convert(converter = UserTypeConverter.class)
    private UserType userType;

    @Convert(converter = GenderConverter.class)
    private Gender gender;                  // 성별
}

