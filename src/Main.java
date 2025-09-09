import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readIntInput("Choose task (1-4) or 0 to exit: ");

            switch (choice) {
                case 0:
                    System.out.println("Exiting program...");
                    return;
                case 1:
                    task1();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println("\n" + "=".repeat(40) + "\n");
        }
    }

    private static void printMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Task 1: Expression calculation");
        System.out.println("0. Exit");
    }

    private static int readIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter an integer: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private static double readDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    // =============== TASK 1 ===============
    private static void task1() {
        System.out.println("\nTask 1: Expression calculation");
        System.out.println("1.Double input --> Double output");
        System.out.println("2.Integer input --> Double output");
        System.out.println("3.Double input --> Integer output");

        int choice = readIntInput("Choose calculation type (1-3): ");

        switch (choice) {
            case 1:
                calculateDoubleDouble();
                break;
            case 2:
                calculateIntDouble();
                break;
            case 3:
                calculateDoubleInt();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static double calculateExpression(double a, double b) {
        return ((a * b) - (a + b) * (a - b)) / (Math.pow(b, 4) + Math.pow(a, 3)) + (5 * b);
    }

    private static void calculateDoubleDouble() {
        double a = readDoubleInput("Enter value for a (double): ");
        double b = readDoubleInput("Enter value for b (double): ");

        double result = calculateExpression(a, b);
        System.out.printf("Result (double): %.4f%n", result);
    }

    private static void calculateIntDouble() {
        int a = readIntInput("Enter value for a (integer): ");
        int b = readIntInput("Enter value for b (integer): ");

        double result = calculateExpression(a, b);
        System.out.printf("Result (double): %.4f%n", result);
    }

    private static void calculateDoubleInt() {
        double a = readDoubleInput("Enter value for a (double): ");
        double b = readDoubleInput("Enter value for b (double): ");

        int result = (int) calculateExpression(a, b);
        System.out.printf("Result (integer): %d%n", result);
    }

}