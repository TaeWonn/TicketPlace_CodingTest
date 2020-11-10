package com.example.demo.dto.movie;

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
public class Country {

    @Id @GeneratedValue
    @Column(name = "COUNTRY_ID")
    private Long id;

    private String name;

    private String continent;

    private String capital;

}
