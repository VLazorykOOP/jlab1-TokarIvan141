import java.util.*;

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
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
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
        System.out.println("2. Task 2: Array union calculation");
        System.out.println("3. Task 3: Matrix column rearrangement");
        System.out.println("4. Task 4: Text analysis");
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
        double denominator = Math.pow(b, 4) + Math.pow(a, 3);

        if (denominator == 0)
            throw new ArithmeticException("Division by zero");


        return ((a * b) - (a + b) * (a - b)) / denominator + (5 * b);
    }

    private static void calculateDoubleDouble() {
        try {
            double a = readDoubleInput("Enter value for a (double): ");
            double b = readDoubleInput("Enter value for b (double): ");

            double result = calculateExpression(a, b);
            System.out.printf("Result (double): %.4f%n", result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void calculateIntDouble() {
        try {
            int a = readIntInput("Enter value for a (integer): ");
            int b = readIntInput("Enter value for b (integer): ");

            double result = calculateExpression(a, b);
            System.out.printf("Result (double): %.4f%n", result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void calculateDoubleInt() {
        try {
            double a = readDoubleInput("Enter value for a (double): ");
            double b = readDoubleInput("Enter value for b (double): ");

            int result = (int) calculateExpression(a, b);
            System.out.printf("Result (integer): %d%n", result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // =============== TASK 2 ===============
    private static void task2() {
        int[] arrayA = new int[200];
        int[] arrayB = new int[200];

        for (int i = 0; i < 200; i++) {
            arrayA[i] = (int) (Math.random() * 101);
            arrayB[i] = (int) (Math.random() * 101);
        }

        Set<Integer> unionSet = new HashSet<>();
        for (int num : arrayA) unionSet.add(num);
        for (int num : arrayB) unionSet.add(num);

        Integer[] unionArray = unionSet.toArray(Integer[]::new);
        Arrays.sort(unionArray);

        int sum = Arrays.stream(unionArray).mapToInt(Integer::intValue).sum();

        System.out.println("Array A: " + Arrays.toString(arrayA));
        System.out.println("Array B: " + Arrays.toString(arrayB));
        System.out.println("Union of A and B: " + Arrays.toString(unionArray));
        System.out.println("Sum of union elements: " + sum);
    }

    // =============== TASK 3 ===============
    private static void task3() {
        int n = readMatrixSize();
        double[][] matrix = new double[n][n];

        System.out.println("Enter matrix elements:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = readDoubleInput("Element [" + i + "][" + j + "]: ");
            }
        }

        System.out.println("Original matrix:");
        printMatrix(matrix);

        int minCol = findColumnWithMinElement(matrix);
        System.out.println("Column with minimum element: " + minCol);

        double[][] rearrangedMatrix = rearrangeColumns(matrix, minCol);

        System.out.println("Rearranged matrix:");
        printMatrix(rearrangedMatrix);
    }

    private static int readMatrixSize() {
        int n;
        do {
            n = readIntInput("Enter matrix size n (n < 20): ");
            if (n >= 20) {
                System.out.println("Size must be less than 20!");
            }
        } while (n >= 20);
        return n;
    }

    private static int findColumnWithMinElement(double[][] matrix) {
        double min = matrix[0][0];
        int minCol = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minCol = j;
                }
            }
        }

        return minCol;
    }

    private static double[][] rearrangeColumns(double[][] matrix, int minCol) {
        int n = matrix.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            result[i][0] = matrix[i][minCol];
        }

        int resultCol = 1;
        for (int j = 0; j < n; j++) {
            if (j != minCol) {
                for (int i = 0; i < n; i++) {
                    result[i][resultCol] = matrix[i][j];
                }
                resultCol++;
            }
        }

        return result;
    }

    private static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%7.2f", matrix[i][j]);
            }
            System.out.println();
        }
    }

    // =============== TASK 4 ===============
    private static void task4() {
        System.out.println("\nTask 4: Text analysis");

        System.out.println("Enter text:");
        String text = scanner.nextLine();

        System.out.println("Enter character to find:");
        char target = scanner.nextLine().charAt(0);

        String[] words = getWords(text);
        List<String> result = findWords(words, target);

        if (result.isEmpty()) {
            System.out.println("No words with character '" + target + "'");
        } else {
            System.out.println("Words with most '" + target + "':");
            result.forEach(System.out::println);
        }
    }

    private static String[] getWords(String text) {
        return text.replaceAll("[^a-zA-Zа-яА-Я\\s]", "").split("\\s+");
    }

    private static List<String> findWords(String[] words, char target) {
        int maxCount = 0;
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (word.isEmpty()) continue;

            long count = word.chars()
                    .filter(c -> Character.toLowerCase(c) == Character.toLowerCase(target))
                    .count();

            if (count > maxCount) {
                maxCount = (int)count;
                result.clear();
                result.add(word);
            } else if (count == maxCount && count > 0) {
                result.add(word);
            }
        }

        return result;
    }
}