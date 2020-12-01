import java.util.Scanner;

public class App {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String [] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while(true) {
            System.out.print("\nSelect Your Application\n");
            System.out.print("-----------------------\n\n");
            System.out.println("1) task list");
            System.out.println("2) contact list");
            System.out.println("3) quit");

            String choice = scan.nextLine();

            switch(choice) {
                case "1" -> TaskApp.mainTaskMenu();
                case "2" -> ContactApp.mainContactMenu();
                case "3" -> System.exit(0);
                default -> System.out.println("\nNot a valid selection. Please try again.\n");
            }
        }
    }
}
