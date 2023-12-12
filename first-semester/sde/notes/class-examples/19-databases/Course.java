public class Course {
    private int crn;
    private String subject;
    private int number;
    private int section;
    private String meetingTime;
    private Lecturer lecturer;

    public Course() { }

    public Course(int crn, String subject, int number, int section, String meetingTime, Lecturer lecturer) {
        this.crn = crn;
        this.subject = subject;
        this.number = number;
        this.section = section;
        this.meetingTime = meetingTime;
        this.lecturer = lecturer;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return crn == course.crn;
    }

    @Override
    public int hashCode() {
        return crn;
    }

    @Override
    public String toString() {
        return "Course{" +
                "crn=" + crn +
                ", subject='" + subject + '\'' +
                ", number=" + number +
                ", section=" + section +
                ", meetingTime='" + meetingTime + '\'' +
                ", lecturer=" + lecturer +
                '}';
    }
}
