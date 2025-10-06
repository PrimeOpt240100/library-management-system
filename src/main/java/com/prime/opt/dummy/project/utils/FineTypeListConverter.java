package com.prime.opt.dummy.project.utils;

import com.prime.opt.dummy.project.Enum.card_enum.FineType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Convert
public class FineTypeListConverter implements AttributeConverter<List<FineType>, String> {

    @Override
    public String convertToDatabaseColumn(List<FineType> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<FineType> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return List.of();
        }
        return Arrays.stream(dbData.split(","))
                .map(FineType::valueOf)
                .collect(Collectors.toList());
    }
}
