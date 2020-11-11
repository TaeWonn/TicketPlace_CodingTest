package com.example.demo.dto.movie.enums;

import com.example.demo.conf.converter.LegacyCommonType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType implements LegacyCommonType {
    AUDIENCE("관람객","0"),
    CRITIC("평론가","1");

    private String desc;
    private String legacyCode;

}
