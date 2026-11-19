public class Student {

    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // format used when saving into the file
    public String toFileString() {
        return id + "," + name + "," + age + "," + course;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-5d %-15s", id, name, age, course);
    }
}
