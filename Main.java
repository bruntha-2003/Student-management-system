import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static 1StudentService service = new StudentService();

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("Thank you! Bye.");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 6);
    }

    // keeps asking until the user types a proper number
    static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            }
        }
    }

    static String readText(String message) {
        String text;
        do {
            System.out.print(message);
            text = sc.nextLine().trim();
            if (text.isEmpty() || text.contains(",")) {
                System.out.println("Value cannot be empty or contain a comma.");
            }
        } while (text.isEmpty() || text.contains(","));
        return text;
    }

    static void addStudent() {
        int id = readInt("Enter ID: ");
        String name = readText("Enter name: ");
        int age = readInt("Enter age: ");
        String course = readText("Enter course: ");

        Student s = new Student(id, name, age, course);
        if (service.addStudent(s)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with this ID already exists.");
        }
    }

    static void viewStudents() {
        ArrayList<Student> list = service.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println(String.format("%-5s %-20s %-5s %-15s", "ID", "Name", "Age", "Course"));
        System.out.println("---------------------------------------------------");
        for (Student s : list) {
            System.out.println(s);
        }
    }

    static void searchStudent() {
        int id = readInt("Enter ID to search: ");
        Student s = service.findById(id);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(s);
        }
    }

    static void updateStudent() {
        int id = readInt("Enter ID to update: ");
        if (service.findById(id) == null) {
            System.out.println("Student not found.");
            return;
        }
        String name = readText("Enter new name: ");
        int age = readInt("Enter new age: ");
        String course = readText("Enter new course: ");
        service.updateStudent(id, name, age, course);
        System.out.println("Student updated successfully.");
    }

    static void deleteStudent() {
        int id = readInt("Enter ID to delete: ");
        if (service.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
}
