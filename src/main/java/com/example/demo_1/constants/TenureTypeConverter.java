package com.example.demo_1.constants;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TenureTypeConverter implements AttributeConverter<TenureType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TenureType attribute) {
        return attribute.getTenureType();
    }

    @Override
    public TenureType convertToEntityAttribute(Integer dbData) {
        return TenureType.getTenureTypeFromValue(dbData);
    }

}
