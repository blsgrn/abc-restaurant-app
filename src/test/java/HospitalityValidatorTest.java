
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

    HospitalityValidator.validate(hospitality);
  }

  @Test
  void testInvalidName() {
    Hospitality hospitality = new Hospitality(new ObjectId(), "", "Valid Description", 9.99, "Category",
        "http://example.com/image.jpg");

    assertThrows(IllegalArgumentException.class, () -> HospitalityValidator.validate(hospitality));
  }

  @Test
  void testInvalidPrice() {
    Hospitality hospitality = new Hospitality(new ObjectId(), "Valid Name", "Valid Description", -1.00, "Category",
        "http://example.com/image.jpg");

    assertThrows(IllegalArgumentException.class, () -> HospitalityValidator.validate(hospitality));
  }

  @Test
  void testInvalidImageUrl() {
    Hospitality hospitality = new Hospitality(new ObjectId(), "Valid Name", "Valid Description", 9.99, "Category",
        "invalid-url");

    assertThrows(IllegalArgumentException.class, () -> HospitalityValidator.validate(hospitality));
  }
}
