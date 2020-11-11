package com.example.demo.conf.converter.movie;

import com.example.demo.conf.converter.AbstractLegacyEnumAttributeConverter;
import com.example.demo.dto.movie.enums.Rank;

import javax.persistence.Converter;

@Converter
public class RankConverter extends AbstractLegacyEnumAttributeConverter<Rank> {

    public static final String ENUM_NAME = "등급";

    public RankConverter() {super(Rank.class,true,ENUM_NAME);}
}
