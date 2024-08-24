import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.balasegaran.AbcRestuarant.Validation.UserRegistration;

public class UserRegistrationTest {
  @Test
  public void testValidUsername() {
    assertTrue(UserRegistration.isValidUsername("validUser123"));
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
    assertTrue(UserRegistration.isValidEmail("valid.email@example.com"));
    assertFalse(UserRegistration.isValidEmail("invalid-email"));
    assertFalse(UserRegistration.isValidEmail(null));
  }
}
