import java.io.*;
import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public StudentService() {
        loadFromFile();
    }

    public boolean addStudent(Student s) {
        if (findById(s.getId()) != null) {
            return false; // id already there
        }
        students.add(s);
        saveToFile();
        return true;
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudent(int id, String name, int age, String course) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setAge(age);
        s.setCourse(course);
        saveToFile();
        return true;
    }

    public boolean deleteStudent(int id) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        students.remove(s);
        saveToFile();
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // first run, no file yet
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String course = parts[3];
                    students.add(new Student(id, name, age, course));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }
}
