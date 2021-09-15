package com.example.demo_1.constants;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PartnerTypeConverter implements AttributeConverter<PartnerType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PartnerType attribute) {
        return attribute.getPartnerType();
    }

    @Override
    public PartnerType convertToEntityAttribute(Integer dbData) {
        return PartnerType.fromPartnerType(dbData);
    }
}
