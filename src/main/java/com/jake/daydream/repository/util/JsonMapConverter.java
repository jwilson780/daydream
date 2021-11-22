package com.jake.daydream.repository.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jake.daydream.repository.exception.ConversionException;
import java.io.IOException;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter
public abstract class JsonMapConverter<K, V> implements AttributeConverter<Map<K, V>, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Class<K> keyType;
  private final Class<V> valueType;

  public JsonMapConverter(Class<K> keyType, Class<V> valueType) {
    this.keyType = keyType;
    this.valueType = valueType;
  }

  @Override
  public String convertToDatabaseColumn(Map<K, V> attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      throw new ConversionException(e.getMessage());
    }
  }

  @Override
  public Map<K, V> convertToEntityAttribute(String dbData) {
    if (StringUtils.isEmpty(dbData)) {
      return null;
    }
    try {
      JavaType javaType =
          objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
      return objectMapper.readValue(dbData, javaType);
    } catch (IOException e) {
      throw new ConversionException(e.getMessage());
    }
  }
}
