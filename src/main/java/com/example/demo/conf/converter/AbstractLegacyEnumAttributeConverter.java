package com.example.demo.conf.converter;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

/*
 * https://woowabros.github.io/experience/2019/01/09/enum-converter.html 참고하여 작성됨.
*/
@AllArgsConstructor
public class AbstractLegacyEnumAttributeConverter<E extends Enum<E> & LegacyCommonType>
                                                            implements AttributeConverter<E, String> {
    /**
     *  대상 Enum 클래스의 {@link Class} 객체
     */
    private Class<E> targetEnumClass;

    /**
     * <code>nullable = false</code> 이면, 변환할 값이 null 로 들어왔을 때 예외가 발생한다.
     * <code>nullable = false</code> 이면 변환할 값이 null 일때, 예외 없이 실행하며,
     * 특히 legacy code 로 변환시에는 빈 문자열("")로 반환한다.
     */
    private boolean nullable;

    /**
     * <code>nullable = false</code>일 때 출력할 오류 메시지에 enum에 대한 설명을 위해 Enum 의 설명적 이름을 받는다.
     */
    private String enumName;

    @Override
    public String convertToDatabaseColumn(E attribute) {
        if(!nullable && attribute == null){
            throw new IllegalArgumentException(String.format("%s(은)는 NULL 로 저장할 수 없습니다.",enumName));
        }
        return LegacyEnumValueConvertUtils.toLegacyCode(attribute);
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if(!nullable && StringUtils.isEmpty(dbData)){
            throw new IllegalArgumentException(String.format("%s(이)가 DB에 NULL 혹은 Empty 로(%s) 저장되어 있습니다."
                                                                    ,enumName,dbData));
        }
        return LegacyEnumValueConvertUtils.ofLegacyCode(targetEnumClass, dbData);
    }
}
