public class BodyMassIndex {
    double bmiNumber;
    String bmiCategory;

    public static void isInputPositive(double inputArg) {
        if (inputArg < 0) {
            System.out.println("Invalid input. Please try again.");
            java.lang.System.exit(0);
        } else {
        }
    }

    public double calculateBmi(double heightArt, double weightArg) {
        return 703 * weightArg / (heightArt * heightArt);
    }

    public String calculateBmiCategory(double bmiNumberArg) {
        if (18.5 <= bmiNumberArg && bmiNumberArg <= 24.9) {
            bmiCategory = "normal";
        } else if (25 <= bmiNumberArg && bmiNumberArg <= 29.9) {
            bmiCategory = "overweight";
        } else if (bmiNumberArg >= 30) {
            bmiCategory = "obese";
        } else {
            bmiCategory = "underweight";
        }
        return bmiCategory;
    }

    public BodyMassIndex(double heightArg, double weightArg) {
        bmiNumber = calculateBmi(heightArg, weightArg);
        bmiNumber = Math.round(bmiNumber * 10) / 10.0;
        bmiCategory = calculateBmiCategory(bmiNumber);
    }
}
