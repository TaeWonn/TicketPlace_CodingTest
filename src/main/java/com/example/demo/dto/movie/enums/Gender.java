package com.example.demo.dto.movie.enums;

import com.example.demo.conf.converter.LegacyCommonType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender implements LegacyCommonType {
    MALE("남자","0"),
    FEMALE("여자","1");

    private String desc;
    private String legacyCode;

}
