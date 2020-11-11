package com.example.demo.conf.converter;

import com.example.demo.conf.exceptoins.EnumConvertServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.EnumSet;

/*
* https://woowabros.github.io/experience/2019/01/09/enum-converter.html 를 참고하여 만듬.
*/
/**
 * {@Link LegacyCommonType} enum 을 String 으로 변환하는 유틸
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LegacyEnumValueConvertUtils{


    public static <T extends  Enum<T> & LegacyCommonType> T ofLegacyCode(Class<T> enumClass,
                                                                         String legacyCode) {
        if(StringUtils.isEmpty(legacyCode)){
            return null;
        }

        return EnumSet.allOf(enumClass).stream()
                .filter(v -> v.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElseThrow(() -> new EnumConvertServiceException(
                        String.format("enums=[%s], legacyCode=[%s]가 존재하지 않습니다.",
                                            enumClass.getName(), legacyCode)
                ));
    }


    public static <T extends Enum<T> & LegacyCommonType> String toLegacyCode(T enumValue) {
        if(enumValue == null) return "";

        return enumValue.getLegacyCode();
    }

}
