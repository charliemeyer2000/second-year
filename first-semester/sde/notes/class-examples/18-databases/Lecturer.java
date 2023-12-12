public class Lecturer {
    private int id;
    private String firstName;
    private String lastName;
    private String computingId;

    public Lecturer() { }

    public Lecturer(int id, String firstName, String lastName, String computingId) {
        if (firstName == null || lastName == null || computingId == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.computingId = computingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComputingId() {
        return computingId;
    }

    public void setComputingId(String computingId) {
        this.computingId = computingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecturer lecturer = (Lecturer) o;

        return id == lecturer.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", computingId='" + computingId + '\'' +
                '}';
    }
}
