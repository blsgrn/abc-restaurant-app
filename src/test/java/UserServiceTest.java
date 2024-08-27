import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.balasegaran.AbcRestuarant.Model.User;
import com.balasegaran.AbcRestuarant.Service.UserService;

public class UserServiceTest {

  @InjectMocks
  private UserService userService;

  // Initialize mocks
  public UserServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testValidUser() {
    User validUser = new User();
    validUser.setUsername("john_doe123");
    validUser.setPassword("StrongP@aaw0rd");
    validUser.setEmail("john.doe123@example.com");

    assertDoesNotThrow(() -> userService.validateUser(validUser));
  }

  @Test
  public void testInvalidUsername() {
    User invalidUser = new User();
    invalidUser.setUsername("invalid@user");
    invalidUser.setPassword("StrongP@aaw0rd");
    invalidUser.setEmail("john.doe123@example.com");

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      userService.validateUser(invalidUser);
    });

    assertEquals("Invalid username: Must be alphanumeric and can include underscores.", exception.getMessage());
  }

  @Test
  public void testInvalidPassword() {
    User invalidUser = new User();
    invalidUser.setUsername("john_doe123");
    invalidUser.setPassword("weakpass");
    invalidUser.setEmail("john.doe123@example.com");

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      userService.validateUser(invalidUser);
    });

    assertEquals(
        "Invalid password: Must be at least 8 characters long, with at least one uppercase letter, one lowercase letter, one digit, and one special character.",
        exception.getMessage());
  }

  @Test
  public void testInvalidEmail() {
    User invalidUser = new User();
    invalidUser.setUsername("john_doe123");
    invalidUser.setPassword("StrongP@aaw0rd");
    invalidUser.setEmail("invalid-email");

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      userService.validateUser(invalidUser);
    });

    assertEquals("Invalid email address: Must be a valid email format.", exception.getMessage());
  }
}
