package com.example.demo.conf.converter.movie;

import com.example.demo.conf.converter.AbstractLegacyEnumAttributeConverter;
import com.example.demo.dto.movie.enums.UserType;

import javax.persistence.Converter;

@Converter
public class UserTypeConverter extends AbstractLegacyEnumAttributeConverter<UserType> {
   public static final  String ENUM_NAME = "유저유형";

   public UserTypeConverter() { super(UserType.class,true, ENUM_NAME); }
}
