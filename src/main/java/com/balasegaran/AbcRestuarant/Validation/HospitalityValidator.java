package com.balasegaran.AbcRestuarant.Validation;

import com.balasegaran.AbcRestuarant.Model.Hospitality;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HospitalityValidator {

  private static final String URL_REGEX = "^(http|https)://.*|^/.*$";
  private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

  public static void validate(Hospitality hospitality) {
    if (hospitality.getName() == null || hospitality.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }

    if (hospitality.getDescription() == null || hospitality.getDescription().trim().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty");
    }

    if (hospitality.getPrice() < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }

    if (hospitality.getCategory() == null || hospitality.getCategory().trim().isEmpty()) {
      throw new IllegalArgumentException("Category cannot be null or empty");
    }

    if (hospitality.getImageUrl() != null && !hospitality.getImageUrl().trim().isEmpty()) {
      Matcher matcher = URL_PATTERN.matcher(hospitality.getImageUrl());
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Invalid URL format for image");
      }
    }
  }
}
