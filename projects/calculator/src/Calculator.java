import java.util.Scanner;

public class Calculator {
    private static final String WELCOME_MESSAGE = "Simple Calculator";
    private static final String OPERATION_PROMPT = "Choose operation: + - * / (or 'quit' to exit)";
    private static final String NUMBER_PROMPT = "Enter number: ";
    private static final String RESULT_FORMAT = "Result: %s%n";

    public static void main(String[] arguments) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayWelcome();
            runCalculatorLoop(scanner);
        }
    }

    private static void displayWelcome() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(OPERATION_PROMPT);
    }

    private static void runCalculatorLoop(Scanner scanner) {
        String operation = getNextOperation(scanner);

        while (!isQuitCommand(operation)) {
            double firstNumber = getNextNumber(scanner);
            double secondNumber = getNextNumber(scanner);
            double result = performCalculation(firstNumber, secondNumber, operation);

            if (result != Double.NaN) {
                System.out.printf(RESULT_FORMAT, result);
            }

            operation = getNextOperation(scanner);
        }

        displayGoodbye();
    }

    private static String getNextOperation(Scanner scanner) {
        System.out.print("Operation: ");
        return scanner.nextLine().strip();
    }

    private static double getNextNumber(Scanner scanner) {
        while (true) {
            System.out.print(NUMBER_PROMPT);
            String input = scanner.nextLine().strip();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static double performCalculation(double first, double second, String operation) {
        switch (operation) {
            case "+" -> {
                return first + second;
            }
            case "-" -> {
                return first - second;
            }
            case "*" -> {
                return first * second;
            }
            case "/" -> {
                return handleDivision(first, second);
            }
            default -> {
                System.out.println("Unknown operation: " + operation);
                return Double.NaN;
            }
        }
    }

    private static double handleDivision(double dividend, double divisor) {
        if (divisor == 0) {
            System.out.println("Cannot divide by zero.");
            return Double.NaN;
        }
        return dividend / divisor;
    }

    private static boolean isQuitCommand(String input) {
        return input.equalsIgnoreCase("quit");
    }

    private static void displayGoodbye() {
        System.out.println("Goodbye!");
    }
}
