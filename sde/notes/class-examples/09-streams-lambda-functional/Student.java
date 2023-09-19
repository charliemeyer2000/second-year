import java.util.Comparator;

public class Student implements Comparable<Student> {
    private final String firstName, lastName;
    private final double gpa;
    private final Level level;

    public Student(String firstName, String lastName, double gpa, Level level) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public double getGpa() {
        return gpa;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCombinedName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gpa=" + gpa +
                ", level=" + level +
                '}';
    }

    @Override
    public int compareTo(Student student) {
        return this.getLastName().compareToIgnoreCase(student.getLastName());
    }
}
