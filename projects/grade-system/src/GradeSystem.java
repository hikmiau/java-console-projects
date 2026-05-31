import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeSystem {
  private static final double MINIMUM_PASSING_AVERAGE = 6.0;

  public static void main(String[] arguments) {
    Scanner scanner = new Scanner(System.in);
    List<Student> students = readStudents(scanner);

    printReport(students);
  }

  private static List<Student> readStudents(Scanner scanner) {
    List<Student> students = new ArrayList<>();

    while (true) {
      System.out.print("Student name or empty to finish: ");
      String name = scanner.nextLine().trim();

      if (name.isEmpty()) {
        return students;
      }

      double firstGrade = readGrade(scanner, "First grade: ");
      double secondGrade = readGrade(scanner, "Second grade: ");

      students.add(new Student(name, firstGrade, secondGrade));
    }
  }

  private static double readGrade(Scanner scanner, String label) {
    while (true) {
      System.out.print(label);
      String input = scanner.nextLine().trim();

      if (isInvalidNumber(input)) {
        System.out.println("Type a valid number.");
        continue;
      }

      double grade = Double.parseDouble(input);

      if (isInvalidGrade(grade)) {
        System.out.println("Grade must be between 0 and 10.");
        continue;
      }

      return grade;
    }
  }

  private static boolean isInvalidNumber(String input) {
    try {
      Double.parseDouble(input);
      return false;
    } catch (NumberFormatException exception) {
      return true;
    }
  }

  private static boolean isInvalidGrade(double grade) {
    return grade < 0 || grade > 10;
  }

  private static void printReport(List<Student> students) {
    if (students.isEmpty()) {
      System.out.println("No students registered.");
      return;
    }

    System.out.println();
    System.out.println("Grade report");

    for (Student student : students) {
      printStudent(student);
    }
  }

  private static void printStudent(Student student) {
    String status = student.isApproved() ? "Approved" : "Failed";

    System.out.printf(
        "%s | Average: %.2f | %s%n",
        student.name(),
        student.average(),
        status);
  }

  private record Student(String name, double firstGrade, double secondGrade) {
    private double average() {
      return (firstGrade + secondGrade) / 2;
    }

    private boolean isApproved() {
      return average() >= MINIMUM_PASSING_AVERAGE;
    }
  }
}
