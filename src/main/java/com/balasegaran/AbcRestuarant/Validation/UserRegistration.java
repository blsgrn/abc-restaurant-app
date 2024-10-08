package com.balasegaran.AbcRestuarant.Validation;

public class UserRegistration {

  public static boolean isValidUsername(String username) {
    return username != null && username.matches("[A-Za-z0-9_]*");
  }

  public static boolean isValidPassword(String password) {
    if (password == null)
      return false;
    return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
        && password.matches(".*\\d.*") && password.matches(".*[@#$%^&+=].*");
  }

  public static boolean isValidEmail(String email) {
    return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
  }

  public static boolean isValidName(String name) {
    return name != null && name.matches("[A-Za-z ]{2,}");
  }

  public static boolean isValidContactNumber(String contactNumber) {
    return contactNumber != null && contactNumber.matches("\\d{10}");
  }

  public static boolean isValidSubscription(String role, boolean subscribe) {
    if ("Customer".equalsIgnoreCase(role)) {
      return subscribe;
    }
    return !subscribe;
  }

}
