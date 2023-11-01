import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) {
        var database = new Database();
        try {
            database.connect();

            database.createTables();

            var lecturers = database.getLecturers();
            System.out.println(lecturers);

            var mcburney = database.getLecturerById(1).orElseThrow();
            System.out.println(mcburney);

//            var sherriff = new Lecturer(4, "Mark", "Sherriff", "mss2x");
//            database.addLecturer(sherriff);
//            database.commit();

            var courses = database.getCourses();
            System.out.println(courses);

            database.disconnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
