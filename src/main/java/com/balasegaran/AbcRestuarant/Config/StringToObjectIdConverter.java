package com.balasegaran.AbcRestuarant.Config;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToObjectIdConverter implements Converter<String, ObjectId> {

  @Override
  public ObjectId convert(@NonNull String source) {
    return new ObjectId(source);
  }
}
