import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.balasegaran.AbcRestuarant.Validation.UserRegistration;

public class UserRegistrationTest {

  @Test
  public void testValidUsername() {
    assertTrue(UserRegistration.isValidUsername("john_doe1234"));
    assertFalse(UserRegistration.isValidUsername("in@validUser"));
    assertFalse(UserRegistration.isValidUsername(null));
  }

  @Test
  public void testValidPassword() {
    assertTrue(UserRegistration.isValidPassword("StrongP@aaw0rd"));
    assertFalse(UserRegistration.isValidPassword("weak"));
    assertFalse(UserRegistration.isValidPassword(null));
  }

  @Test
  public void testValidEmail() {
    assertTrue(UserRegistration.isValidEmail("john.doe123@example.com"));
    assertFalse(UserRegistration.isValidEmail("invalid-email"));
    assertFalse(UserRegistration.isValidEmail(null));
  }

  @Test
  public void testValidName() {
    assertTrue(UserRegistration.isValidName("John Doe"));
    assertFalse(UserRegistration.isValidName("J"));
    assertFalse(UserRegistration.isValidName(null));
    assertFalse(UserRegistration.isValidName("John123"));
  }

  @Test
  public void testValidContactNumber() {
    assertTrue(UserRegistration.isValidContactNumber("9876543210"));
    assertFalse(UserRegistration.isValidContactNumber("12345"));
    assertFalse(UserRegistration.isValidContactNumber("12345678901"));
    assertFalse(UserRegistration.isValidContactNumber(null));
    assertFalse(UserRegistration.isValidContactNumber("123abc7890"));
  }

  @Test
  public void testValidSubscription() {
    // Customer subscribing should be valid
    assertTrue(UserRegistration.isValidSubscription("Customer", true));

    // Admin subscribing should not be valid
    assertFalse(UserRegistration.isValidSubscription("Admin", true));

    // Staff subscribing should not be valid
    assertFalse(UserRegistration.isValidSubscription("Staff", true));

  }

}
