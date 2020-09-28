import java.util.ArrayList;
import java.util.Scanner;

public class App {
    // DON'T TOUCH THIS NO-NO SQUARE MAIN()
    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }



    public static boolean moreInput() {
        Scanner bool = new Scanner(System.in);
        System.out.println("Do you have more data to enter? Enter Y or N.");
        char input = bool.next().charAt(0);
        return (input == 'Y' || input == 'y');
    }

    public static double getUserHeight() {
        Scanner scanHeight = new Scanner(System.in);
        System.out.print("Please enter your height (inches): ");
        double h = scanHeight.nextDouble();
        BodyMassIndex.isInputPositive(h);
        return h;
    }

    public static double getUserWeight() {
        Scanner scanWeight = new Scanner(System.in);
        System.out.print("Please enter your weight (pounds): ");
        double w = scanWeight.nextDouble();
        BodyMassIndex.isInputPositive(w);
        return w;
    }

    public static void displayBmiInfo(BodyMassIndex bmiArg) {
        System.out.println("Your BMI is: " + bmiArg.bmiNumber);
        System.out.println("Your BMI category is: " + bmiArg.bmiCategory);
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiDataArg) {
        double sum = 0;
        if (bmiDataArg.isEmpty()) {
            System.out.print("Average BMI number: 0");
        } else {
            for (int i = 0; i < bmiDataArg.size() ; i++) {
                sum += bmiDataArg.get(i).bmiNumber;
            }
        }
        double average = sum / bmiDataArg.size();
        System.out.println("Average BMI: " + average);
    }
}
