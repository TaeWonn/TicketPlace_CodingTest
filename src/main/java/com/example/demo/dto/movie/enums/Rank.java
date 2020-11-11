package com.example.demo.dto.movie.enums;

import com.example.demo.conf.converter.LegacyCommonType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rank implements LegacyCommonType {
    ALL("전체관람가","0"),
    AGE12("12세이상 관람가","1"),
    AGE15("15세이상 관람가","2"),
    AGE18("18세이상 관람가","3");

    private String desc;
    private String legacyCode;
}
