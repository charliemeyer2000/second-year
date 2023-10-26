import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {
    private static final String SQLITE_FILENAME = "mydb.sqlite";

    private Connection connection;

    /* Lecturer Column Names */
    public final String LECTURERS_TABLE = "Lecturers",
            LECTURER_ID = "ID",     // int
        LECTURER_FIRST_NAME = "FirstName",      // String
        LECTURER_LAST_NAME = "LastName",        // String
        LECTURER_COMPUTING_ID = "ComputingId";  // String

    /* Course Column Names */
    public final String COURSE_CRN = "Crn",     // int
        COURSE_SUBJECT = "Subject",             // String
        COURSE_NUMBER = "Number",               // int
        COURSE_SECTION = "Section",             // int
        COURSE_MEETING_TIME = "MeetingTime",    // String
        COURSE_LECTURER = "Lecturer";           // String

    final String createLecturers = """
                create table if not exists Lecturers
                (
                    Id          INTEGER
                        primary key,
                    FirstName   TEXT,
                    LastName    TEXT,
                    ComputingId TEXT
                        unique
                );
                """;
    final String createStudents = """
                create table if not exists Students
                (
                    Id          INTEGER
                        primary key,
                    FirstName   TEXT,
                    LastName    TEXT,
                    ComputingId TEXT
                        unique,
                    ClassYear   INTEGER
                );
                """;
    final String createCourses = """
                create table if not exists Courses
                (
                    crn         INTEGER
                        primary key,
                    Subject     Text,
                    Number      INTEGER,
                    Section     INTEGER,
                    MeetingTime TEXT,
                    Lecturer    integer
                        references Lecturers
                );
                """;
    final String createEnrollment = """
                create table if not exists Enrollments
                (
                    Id      INTEGER
                        primary key,
                    Course  INTEGER not null
                        references Courses (Crn),
                    Student INTEGER not null
                        references Students
                );
                """;

    public Database() {

    }

    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            throw new IllegalStateException("Connection is already open");
        }
        connection = DriverManager.getConnection("jdbc:sqlite:" + SQLITE_FILENAME);
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void disconnect() throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is not currently open");
        }
        connection.close();
    }

    public void createTables() throws SQLException{
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is not open");
        }
        var createTables = List.of(createLecturers, createStudents, createCourses, createEnrollment);
        for (var query : createTables) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }

    public List<Lecturer> getLecturers() throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is not open");
        }
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * from Lecturers");

        var lecturers = new ArrayList<Lecturer>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            var lecturer = getLecturer(resultSet);
            lecturers.add(lecturer);
        }
        statement.close();
        return lecturers;
    }

    public Optional<Lecturer> getLecturerById(int id) throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is not open");
        }
        PreparedStatement statement = connection.prepareStatement(
                String.format("SELECT * from Lecturers WHERE %s = ?", LECTURER_ID));
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (isEmpty(resultSet)) {
            statement.close();
            return Optional.empty();
        }

        var lecturer = getLecturer(resultSet);
        statement.close();
        return Optional.of(lecturer);
    }

    public void addLecturer(Lecturer lecturer) throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is not open");
        }
        PreparedStatement statement = connection.prepareStatement(
                """
                        INSERT INTO Lecturers(Id, FirstName, LastName, ComputingId) values 
                            (?, ?, ?, ?)
                        """);
        statement.setInt(1, lecturer.getId());
        statement.setString(2, lecturer.getFirstName());
        statement.setString(3, lecturer.getLastName());
        statement.setString(4, lecturer.getComputingId());

        int rowsAdded = statement.executeUpdate();

        statement.close();
    }

    public List<Course> getCourses() throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is not open");
        }
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * from Courses");

        var courses = new ArrayList<Course>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            var lecturer= getLecturerById(resultSet.getInt(COURSE_LECTURER)).orElseThrow();

            Course course = new Course();
            course.setCrn(resultSet.getInt(COURSE_CRN));
            course.setSubject(resultSet.getString(COURSE_SUBJECT));
            course.setNumber(resultSet.getInt(COURSE_NUMBER));
            course.setSection(resultSet.getInt(COURSE_SECTION));
            course.setMeetingTime(resultSet.getString(COURSE_MEETING_TIME));
            course.setLecturer(lecturer);
            courses.add(course);
        }
        statement.close();
        return courses;
    }


    private static boolean isEmpty(ResultSet resultSet) throws SQLException {
        return !resultSet.isBeforeFirst() && resultSet.getRow() == 0;
    }

    private Lecturer getLecturer(ResultSet resultSet) throws SQLException {
        var id = resultSet.getInt(LECTURER_ID);
        var firstName = resultSet.getString(LECTURER_FIRST_NAME);
        var lastName = resultSet.getString(LECTURER_LAST_NAME);
        var computingId = resultSet.getString(LECTURER_COMPUTING_ID);
        return new Lecturer(id, firstName, lastName, computingId);
    }



}
