
import com.balasegaran.AbcRestuarant.Model.Hospitality;
import com.balasegaran.AbcRestuarant.Validation.HospitalityValidator;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class HospitalityValidatorTest {

  @Test
  void testValidHospitality() {
    Hospitality hospitality = new Hospitality(new ObjectId(), "Valid Name", "Valid Description", 9.99, "Category",
        "http://example.com/image.jpg");
    // This should pass without exceptions
    HospitalityValidator.validate(hospitality);
  }

  @Test
  void testInvalidName() {
    Hospitality hospitality = new Hospitality(new ObjectId(), "", "Valid Description", 9.99, "Category",
        "http://example.com/image.jpg");
    // This should throw an IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> HospitalityValidator.validate(hospitality));
  }

  @Test
  void testInvalidPrice() {
    Hospitality hospitality = new Hospitality(new ObjectId(), "Valid Name", "Valid Description", -1.00, "Category",
        "http://example.com/image.jpg");
    // This should throw an IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> HospitalityValidator.validate(hospitality));
  }

  @Test
  void testInvalidImageUrl() {
    Hospitality hospitality = new Hospitality(new ObjectId(), "Valid Name", "Valid Description", 9.99, "Category",
        "invalid-url");
    // This should throw an IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> HospitalityValidator.validate(hospitality));
  }
}
