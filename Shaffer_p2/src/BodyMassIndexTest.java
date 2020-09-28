import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// 7 tests, 4 BMI categories, 2 calculatefunctions, 1 isPositive
public class BodyMassIndexTest {
    @Test
    void testCalculateBmi() {
        BodyMassIndex bmi = new BodyMassIndex(0, 0);
        assertEquals(27.3, bmi.calculateBmi(60, 140));
    }

    @Test
    void testCalculateBmiCategory() {
        BodyMassIndex bmi = new BodyMassIndex(0, 0);
        assertEquals(bmi.bmiCategory = "Normal weight", bmi.calculateBmiCategory(24));
    }

    @Test
    void testUnderweight() {
        BodyMassIndex bmi = new BodyMassIndex(72, 136);
        assertEquals("Underweight", bmi.bmiCategory);
    }

    @Test
    void testNormalWeight() {
        BodyMassIndex bmi = new BodyMassIndex(67, 142);
        assertEquals("Normal weight", bmi.bmiCategory);
    }

    @Test
    void testOverweight() {
        BodyMassIndex bmi = new BodyMassIndex(65, 151);
        assertEquals("Overweight", bmi.bmiCategory);
    }

    @Test
    void testObese() {
        BodyMassIndex bmi = new BodyMassIndex(60, 164);
        assertEquals("Obesity", bmi.bmiCategory);
    }
}
