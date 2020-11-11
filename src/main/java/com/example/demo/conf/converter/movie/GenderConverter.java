package com.example.demo.conf.converter.movie;

import com.example.demo.conf.converter.AbstractLegacyEnumAttributeConverter;
import com.example.demo.dto.movie.enums.Gender;

import javax.persistence.Converter;

@Converter
public class GenderConverter extends AbstractLegacyEnumAttributeConverter<Gender> {

    public static final  String ENUM_NAME = "유저유형";

    public GenderConverter() { super(Gender.class,true, ENUM_NAME); }
}
